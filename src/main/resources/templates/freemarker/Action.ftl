package ${basePackage}.${moduleName}.action;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;

import com.sxkj.frame.component.excel.action.ExportExcelAction;
import com.sxkj.frame.utils.ConstVar;
import com.sxkj.frame.utils.PageModel;
import com.sxkj.frame.utils.SxkjAction;
import com.sxkj.frame.utils.LogUtil;
import ${basePackage}.${moduleName}.bean.${table.className};
import ${basePackage}.${moduleName}.service.${table.className}Service;

@SuppressWarnings("serial")
public class ${table.className}Action extends SxkjAction<${table.className}> {

	private ${table.className} ${moduleName}Query = new ${table.className}();
	private PageModel pageModel = new PageModel(ConstVar.PAGE_NUM);
	@Resource(name = "${moduleName}Service")
	private ${table.className}Service ${moduleName}Service;
	private List<${table.className}> ${moduleName}List;
	private ${table.className} ${moduleName};
	
	private String multiSelectData;/* 多选下拉数据源 */

	/* 导出表格 */
	private static InputStream excelFile;
	private String downloadFileName;
	private boolean searchAll;

	public String getMultiSelectData() {
		return multiSelectData;
	}

	public void setMultiSelectData(String multiSelectData) {
		this.multiSelectData = multiSelectData;
	}

	public ${table.className} get${table.className}Query() {
		return  ${moduleName}Query;
	}

	public void set${table.className}Query(${table.className} ${moduleName}Query) {
		this.${moduleName}Query = ${moduleName}Query;
	}

	public ${table.className} get${table.className}(){
		return  ${moduleName};
	}

	public void set${table.className}(${table.className} ${moduleName}) {
		this.${moduleName} = ${moduleName};
	}

	public PageModel getPageModel() {
		return pageModel;
	}

	public void setPageModel(PageModel pageModel) {
		this.pageModel = pageModel;
	}

	public void set${table.className}Service(${table.className}Service ${moduleName}Service){
		this.${moduleName}Service = ${moduleName}Service;
	}

	public ${table.className} getModel() {
		return  this.${moduleName}Query;
	}

	public List<${table.className}> get${table.className}List() {
		return ${moduleName}List;
	}

	/**
	 *
	 * @Title: add
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 新增
	 * @return: String
	 * @throws:
	 */
	public String add() throws Exception {
		this.${moduleName} = this.${moduleName}Service.add();
		return "${moduleName}Add";
	}

	/**
	 *
	 * @Title: list
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 列表页
	 * @return: String
	 * @throws:
	 */
	public String list() throws Exception {
		pageModel.setAction("${moduleName}!list.action");
		this.${moduleName}List = ${moduleName}Service.searchList(this.${moduleName}Query, pageModel);
		return "${moduleName}List";
	}

	/**
	 *
	 * @Title: grid
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description:
	 * @return: String
	 * @throws:
	 */
	public String grid() throws Exception {
		return "${moduleName}Grid";
	}

	/**
	 *
	 * @Title: edit
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 修改
	 * @return: String
	 * @throws:
	 */
	public String edit() throws Exception {
		${moduleName} = ${moduleName}Service.search(${moduleName}Query.getId());
		this.${moduleName}Query.setId(null);
		return "${moduleName}Edit";
	}

	/**
	*
	* @Title: view
	* @author:${author}
	* @date: ${.now?date}
	* @Description: 查看
	* @return: String
	* @throws:
	*/
	public String view() throws Exception {
	this.${moduleName} = ${moduleName}Service.search(${moduleName}Query.getId());
	return "${moduleName}View";
	}

	/* 导出表格相关 */
	public String exportExcel() throws Exception {
		List<${table.className}> objList = new ArrayList<${table.className}>();
		if (searchAll) {
		objList = ${moduleName}Service.searchList(${moduleName}Query, null);
		} else {
			objList = ${moduleName}Service.searchList(${moduleName}Query, pageModel);
		}
		excelFile = ExportExcelAction.exportExcel(${table.className}.class, objList);
		return "exportExcel";
	}

	public static InputStream getExcelFile() {
		return excelFile;
	}
	
	public static void setExcelFile(InputStream excelFile) {
		${table.className}Action.excelFile = excelFile;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getDownloadFileName() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd ");
		downloadFileName = (sf.format(new Date()).toString())	+ " 导出的Excel信息.xls ";
		try {
			downloadFileName = new String(downloadFileName.getBytes(),"ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			LogUtil.exception(e);
		}
		return downloadFileName;
	}
}