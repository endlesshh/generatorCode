package code.pojo;
/**
 * 
 * @author ShiQiang
 * 
 * 需要生成的代码标示
 * 有用户选择，选择哪个生成哪个
 *
 */
public class CreateFileProperty {
	private boolean actionFlag;
	private boolean serviceIFlag;
	private boolean entityFlag;
	private boolean serviceImplFlag;
	private boolean htmlFlag;
	private boolean daoFlag;
	private boolean daoImplFlag;
	private boolean queryFlag;
	
	/**jsp模板的类型   */
	private String htmlMode;

	public boolean isActionFlag() {
		return this.actionFlag;
	}

	public boolean isServiceIFlag() {
		return this.serviceIFlag;
	}

	public boolean isEntityFlag() {
		return this.entityFlag;
	}

	public boolean isServiceImplFlag() {
		return this.serviceImplFlag;
	}

	public void setActionFlag(boolean actionFlag) {
		this.actionFlag = actionFlag;
	}

	public void setServiceIFlag(boolean serviceIFlag) {
		this.serviceIFlag = serviceIFlag;
	}

	public void setEntityFlag(boolean entityFlag) {
		this.entityFlag = entityFlag;
	}

	public void setServiceImplFlag(boolean serviceImplFlag) {
		this.serviceImplFlag = serviceImplFlag;
	}

	public boolean isHtmlFlag() {
		return this.htmlFlag;
	}

	public void setHtmlFlag(boolean htmlFlag) {
		this.htmlFlag = htmlFlag;
	}

	public String getHtmlMode() {
		return this.htmlMode;
	}

	public void setHtmlMode(String htmlMode) {
		this.htmlMode = htmlMode;
	}

	public boolean isDaoFlag() {
		return daoFlag;
	}

	public void setDaoFlag(boolean daoFlag) {
		this.daoFlag = daoFlag;
	}

	public boolean isDaoImplFlag() {
		return daoImplFlag;
	}

	public void setDaoImplFlag(boolean daoImplFlag) {
		this.daoImplFlag = daoImplFlag;
	}

	public boolean isQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(boolean queryFlag) {
		this.queryFlag = queryFlag;
	}
	
}