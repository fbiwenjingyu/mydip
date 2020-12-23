package com.bs.it.book.ui;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bs.it.book.entity.Book;
import com.bs.it.book.service.BookService;
import com.bs.it.book.vo.OrderExt;
import com.bs.it.book.vo.ReadExcelDto;
import com.bs.platform.bps.processInstManager.BpsProcessInstManagerImpl;
import com.bs.platform.bps.processInstManager.BpsProcessInstManagerServiceServiceStub.CreateAndStartProcessInstance;
import com.bs.platform.bps.processInstManager.BpsProcessInstManagerServiceServiceStub.CreateAndStartProcessInstanceResponse;
import com.bs.platform.bps.workItemManager.BpsWorkItemManagerImpl;
import com.bs.platform.bps.workItemManager.BpsWorkItemManagerServiceServiceStub.QueryWorkItemParticipantInfo;
import com.bs.platform.bps.workItemManager.BpsWorkItemManagerServiceServiceStub.QueryWorkItemParticipantInfoResponse;
import com.bs.platform.core.annotations.Log;
import com.bs.platform.core.annotations.WhiteList;
import com.bs.platform.core.redis.RedisCacheUtils;
import com.bs.platform.core.support.BaseCrudController;
import com.bs.platform.core.utils.ExcelUtils;
import com.bs.platform.core.utils.RestTemplateUtils;
import com.bs.platform.core.vo.CommonResult;
import com.bs.platform.core.vo.ExcelLog;
import com.bs.platform.core.vo.ExcelLogs;
import com.bs.platform.core.vo.SheetData;

/**
 * @copyright:primeton.com
 * @author: chh
 * @version: 1.0
 */
@RestController
@RequestMapping("/book")
@Api(tags = "图书管理")
@Log("图书管理")
@WhiteList
public class BookResource extends BaseCrudController<Book, Long> {

	@Resource
	private BookService service;

	@Autowired(required=false)
	private RocketMQTemplate rocketMQTemplate;

	@Resource
	private RedisCacheUtils redisCacheUtils;
	
	@Resource
	private BpsProcessInstManagerImpl bpsManager;
	
	@Resource
	private BpsWorkItemManagerImpl bpsWorkManager;

	@Override
	protected BookService getService() {
		return this.service;
	}
	
	
	/**
	 * 测试获取book列表数据
	 * 
	 * @return
	 */
	@ApiOperation(value = "测试获取book列表数据", notes = "查询book列表数据")
	@ResponseBody
	@RequestMapping(value = "/listAll")
	public CommonResult listAll() {
		List<Book> list = getService().selectList(null);
		return new CommonResult(list);
	}


	/**
	 * 测试多数据源
	 * 
	 * @return
	 */
	@ApiOperation(value = "测试多数据源", notes = "查询salve数据源")
	@ResponseBody
	@RequestMapping(value = "/list4Salve", method = RequestMethod.POST)
	public CommonResult list4Salve(@RequestParam(required = false) String name) {
		logger.info(name);
		return getService().list4Salve();
	}

	/**
	 * rest调用工具类
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testRest")
	public void testRest() {
		String url = "https://www.cnblogs.com/{id}/p/{pageName}.html";
		String id = "jonban";
		List<String> pages = new ArrayList<>();
		pages.add("rest");
		pages.add("jsoup");
		pages.add("sms");
		pages.add("rememberMe");
		pages.add("properties");
		pages.add("async");

		for (String pageName : pages) {
			ResponseEntity<String> entity = RestTemplateUtils.get(url, String.class, id, pageName);
			String code=entity.getStatusCode().toString();
			logger.info(code);
			logger.info(entity.getBody());
		}
	}

	/**
	 * redis工具类
	 * 
	 * @return
	 */
	@ApiOperation(value = "用户列表", notes = "查询用户列表")
	@ResponseBody
	@RequestMapping(value = "/testRedis")
	public void testRedis() {
		redisCacheUtils.set("a1", "111");
		System.out.println(redisCacheUtils.get("a1").toString());
	}

	/**
	 * 测试validation参数校验
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testValidation")
	public void testValidation(@Validated Book book) {
		logger.info(book.getName());
	}

	/**
	 * 测试自动事务配置
	 * 
	 * @return
	 */
	@ApiOperation(value = "测试自动事务配置", notes = "")
	@ResponseBody
	@RequestMapping(value = "/saveThis", method = RequestMethod.POST)
	public void saveThis() {
		logger.info("++++++++++  saveThis ++++++++++++++");
		getService().saveThis();
	}

	/**
	 * 测试excel模板功能
	 * 
	 * @return
	 */
	@ApiOperation(value = "测试excel模板功能", notes = "")
	@ResponseBody
	@RequestMapping(value = "/testExcel", method = RequestMethod.GET)
	public void testExcel() {
		File model = null;
		try {
			model = ResourceUtils.getFile("classpath:com/bs/it/test_excel.xlsx");
		} catch (Exception e1) {
			logger.error(e1.toString());
		}
		if (model == null) {
			logger.info("model is not exists !");
			return;
		}
		File f = new File("D:/test_out.xlsx");
		SheetData[] sds = new SheetData[2];
		// 创建2个数据sheet
		for (int i = 0; i < 2; i++) {
			SheetData sd = new SheetData("测试" + i);
			sd.put("name", "张三" + i);
			sd.put("age", i);
			// 每个sheet页加入100条测试数据
			// 注意这里可以加入pojo也可以直接使用map
			for (int j = 0; j < 100; j++) {
				Book td = new Book();
				td.setId(Long.valueOf(j));
				td.setName("" + j);
				td.setRemark("t" + j);
				sd.addData(td);
			}
			sds[i] = sd;
		}
		try {
			ExcelUtils.writeData(model, new FileOutputStream(f), sds);
			logger.info("done.");
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		}
	}

	/**
	 * 测试springSession共享redis
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testSpringSession", method = RequestMethod.POST)
	public void testSpringSession(HttpServletRequest request) {
		request.getSession().setAttribute("user", "111");
	}

	/**
	 * 测试rocketmq发送消息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/testMq", method = RequestMethod.POST)
	public void testMq(HttpServletRequest request) {
		
		// 如下两种方式等价
		rocketMQTemplate.convertAndSend("test-topic-1", "Hello, World!");
		rocketMQTemplate.send("test-topic-1",
				MessageBuilder.withPayload("Hello, World! test-topic-1的消息").build());
		
		// 第三个参数为timeout send timeout with millis
		rocketMQTemplate.syncSend("test-topic-2", "Hello, World! syncSend", 10000);

		// 消息体为自定义对象
		rocketMQTemplate.convertAndSend("test-topic-2", new OrderExt("001", new Date(), 11L, "test-topic-2的对象消息"));

		// 发送即发即失消息（不关心发送结果）
				rocketMQTemplate.sendOneWay("test-topic-1", MessageBuilder.withPayload("test-topic-1的OneWay消息").build());
		
		// 发送延迟消息
		rocketMQTemplate.syncSend("test-topic-1",MessageBuilder.withPayload(new OrderExt("002", new Date(), 11L, "test-topic-1的延迟消息")).build(), 3000 ,5);

		// 发送异步消息
		rocketMQTemplate.asyncSend("test-topic-1", MessageBuilder.withPayload("test-topic-1的异步消息").build(),
				new SendCallback() {
					@Override
					public void onSuccess(SendResult sendResult) {
						
					}

					@Override
					public void onException(Throwable e) {

					}
				});
		
		// 发送顺序消息
		rocketMQTemplate.syncSendOrderly("test-topic-4", "test-topic-4的顺序消息", "1234");
	}
	
	/**
	 * 测试excel读取功能
	 * 
	 * @return
	 */
	@ApiOperation(value = "测试excel读取功能", notes = "")
	@ResponseBody
	@RequestMapping(value = "/testReadExcel", method = RequestMethod.GET)
	public void testReadExcel() {
		File f = null;
		InputStream inputStream= null;
		try {
			f = ResourceUtils.getFile("classpath:com/bs/it/test_excel_read.xlsx");
			inputStream= new FileInputStream(f);
		} catch (Exception e1) {
			logger.error(e1.toString());
		}
		if (f == null) {
			logger.error("file is not exists !");
			return;
		}
	    
	    ExcelLogs logs =new ExcelLogs();
	    Collection<ReadExcelDto> importExcel = ExcelUtils.readExcel(ReadExcelDto.class, inputStream, "yyyy-MM-dd", logs);
	    
	    // 如果读取有错误，显示出错误信息
	    if(logs.getHasError()){
	    	for(ExcelLog excelLog : logs.getErrorLogList()){
	    		logger.error(excelLog.getLog());
	    	}
	    }
	    
	    // 将读取出的结果打印出来
	    for(ReadExcelDto m : importExcel){
	    	logger.info("name {} age {} sex {} birthDay {} ",
					m.getName(), m.getAge(), m.getSex(), m.getBirthDay());
	    }
	}
	
	@RequestMapping(value = "/bpsTest", method = RequestMethod.GET)
	public void bpsTest() throws Exception {
		CreateAndStartProcessInstance c = new CreateAndStartProcessInstance();
    	c.setIn0("{\"empID\":\"E905506\",\"processDefName\":\"com.bps.test111\",\"processInstName\":\"测试在线流程1111\",\"processInstDesc\":\"流程实例描述\",\"bizNo\":\"222\"}");
    	CreateAndStartProcessInstanceResponse fp=bpsManager.createAndStartProcessInstance(c);
    	logger.info(fp.getOut1());
	}
	
	@RequestMapping(value = "/bpsTest2", method = RequestMethod.GET)
	public void bpsTest2() throws Exception {
		QueryWorkItemParticipantInfo c = new QueryWorkItemParticipantInfo();
    	c.setIn0("{\"empID\":\"E905506\",\"scope\":\"ALL\"}");
    	QueryWorkItemParticipantInfoResponse fp=bpsWorkManager.queryWorkItemParticipantInfo(c);
    	logger.info(fp.getOut1());
	}
	
}
