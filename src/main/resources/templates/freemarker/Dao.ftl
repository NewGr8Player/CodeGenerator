package ${basePackage}.${moduleName}.dao;

import java.util.List;
import com.sxkj.frame.utils.PageModel;

import ${basePackage}.${moduleName}.bean.${table.className};

public interface ${table.className}Dao {

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
	public void save(${table.className} ${moduleName}) throws Exception;

	/**
	 *
	 * @Title: delete
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 删除
	 * @param: id
	 * @return: void
	 * @throws:
	 */
	public void delete(String id) throws Exception;

	/**
	 *
	 * @Title: update
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 更新
	 * @param: ${moduleName}
	 * @return: void
	 * @throws:
	 */
	public void update(${table.className} ${moduleName}) throws Exception;

	/**
	 *
	 * @Title: update
	 * @author:${author}
	 * @date: ${.now?date}
	 * @Description: 更新
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
	 * @Description: 分页查询列表
	 * @param: ${moduleName}
	 * @param: PageModel pageModel
	 * @return: List<${table.className}>
	 * @throws:
	 */
	public List<${table.className}> searchList(${table.className} ${moduleName},  PageModel pageModel) throws Exception;
}