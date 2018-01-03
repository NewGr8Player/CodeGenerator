package ${basePackage}.${moduleName}.service;

import java.util.List;

import com.sxkj.frame.utils.PageModel;
import ${basePackage}.${moduleName}.bean.${table.className};

public interface ${table.className}Service {

	/**
	 *
	 * @Title: add
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 添加
	 * @return: ${table.className}
	 * @throws:
	 */
	public ${table.className} add() throws Exception;

	/**
	 *
	 * @Title: save
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 保存
	 * @param: ${moduleName}
	 * @return: void
	 * @throws:
	 */
	public String save(${table.className} ${moduleName}) throws Exception;

	/**
	 *
	 * @Title: delete
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 删除
	 * @param: id
	 * @return: String
	 * @throws:
	 */
	public String delete(String id) throws Exception;

	/**
	 *
	 * @Title: update
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 更新
	 * @param: ${moduleName}
	 * @return: String
	 * @throws:
	 */
	public String update(${table.className} ${moduleName}) throws Exception;

	/**
	 *
	 * @Title: search
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 搜索
	 * @param: id
	 * @return: ${table.className}
	 * @throws:
	 */
	public ${table.className} search(String id) throws Exception;

	/**
	 *
	 * @Title: searchList
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 搜索返回列表
	 * @param: ${moduleName}
	 * @param: pageModel
	 * @return: List<${table.className}>
	 * @throws:
	 */
	public List<${table.className}> searchList(${table.className} ${moduleName},  PageModel pageModel) throws Exception;
}