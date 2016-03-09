package hello;

/* Code Generator Information.
 * generator Version 1.0.0 release 2007/10/10
 * generated Date Wed Mar 09 16:41:26 JST 2016
 */
import java.io.Serializable;

/**
 * NotifiesVo.
 * @author kiyo
 * @version 1.0
 * history
 * Symbol	Date		Person		Note
 * [1]		2016/03/09	kiyo		Generated.
 */
public class NotifiesVo implements Serializable{

	public static final String TABLE = "NOTIFIES";

	/**
	 * ID:integer(10) <Primary Key>
	 */
	public int id;

	/**
	 * FIRST_NAME:varchar(5)
	 */
	public String first_name;

	/**
	 * S_REG_DATE:timestamp(23,10)
	 */
	public java.sql.Timestamp s_reg_date;

	/**
	* Constractor
	*/
	public NotifiesVo(){}

	/**
	* Constractor
	* @param <code>id</code>
	*/
	public NotifiesVo(int id){
		this.id = id;
	}

	public int getId(){ return this.id; }

	public void setId(int id){ this.id = id; }

	public String getFirst_name(){ return this.first_name; }

	public void setFirst_name(String first_name){ this.first_name = first_name; }

	public java.sql.Timestamp getS_reg_date(){ return this.s_reg_date; }

	public void setS_reg_date(java.sql.Timestamp s_reg_date){ this.s_reg_date = s_reg_date; }

	public String toString(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("[NotifiesVo:");
		buffer.append(" id: ");
		buffer.append(id);
		buffer.append(" first_name: ");
		buffer.append(first_name);
		buffer.append(" s_reg_date: ");
		buffer.append(s_reg_date);
		buffer.append("]");
		return buffer.toString();
	}

}
