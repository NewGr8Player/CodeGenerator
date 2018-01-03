package ${basePackage}.${moduleName}.dao;

import java.util.List;

import com.sxkj.frame.utils.LogUtil;
import com.sxkj.frame.utils.PageModel;
import com.sxkj.frame.utils.PrepareBeanSql;
import ${basePackage}.${moduleName}.bean.${table.className};
import com.sxkj.frame.core.hibernate.dao.HBaseDaoImpl;

import org.hibernate.criterion.Criterion;

public class ${table.className}DaoImpl extends HBaseDaoImpl<${table.className}> implements ${table.className}Dao {

	public void save(${table.className} ${moduleName}) throws Exception{
		super.save(${moduleName});
	}

	public void delete(String id) throws Exception {
		String sql = "delete from ${table.tableName} where id='" + id + "'";
		super.executeSql(sql);
	}

	public void update(${table.className} ${moduleName}) throws Exception {
		super.update(${moduleName});
	}

	public ${table.className} search(String id) throws Exception{
		return super.searchById(id);
	}

	public List<${table.className}>  searchList(${table.className} ${moduleName},  PageModel pageModel) throws Exception {
		String[] exceptions = {"id"};
		List<Criterion> criterions = PrepareBeanSql.prepareSearchListCriterions(${moduleName}, exceptions);
		return super.searchListPageByCriterion(criterions, pageModel);
	}
}
