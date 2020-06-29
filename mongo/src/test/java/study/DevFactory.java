package study;

/**
 * 设备厂商编号
 * 
 * @author yuyifang
 * 
 * @date 2019-03-19
 */
public class DevFactory {

	protected Integer id;

	private String devFactoryId;

	private String devFactoryName;

	private String devType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevFactoryId() {
		return devFactoryId;
	}

	public void setDevFactoryId(String devFactoryId) {
		this.devFactoryId = devFactoryId == null ? null : devFactoryId.trim();
	}

	public String getDevFactoryName() {
		return devFactoryName;
	}

	public void setDevFactoryName(String devFactoryName) {
		this.devFactoryName = devFactoryName == null ? null : devFactoryName.trim();
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}
}