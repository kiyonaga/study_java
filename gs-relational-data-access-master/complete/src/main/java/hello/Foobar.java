package hello;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Foobar {
	private long id;
	private String col1;
	private Date s_reg_date;

	public Foobar() {
	}

	public Foobar(long id, String col1, Date s_reg_date) {
		this.id = id;
		this.col1 = col1;
		this.s_reg_date = s_reg_date;
	}

	public Foobar(long id, String col1, Timestamp s_reg_date) {
		this(id, col1, new Date(s_reg_date.getTime()));
	}

	@Override
	public String toString() {
		return "Foobar[id=" + id + ", col1=" + col1 + ", s_reg_date=" +
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(s_reg_date) + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public void setS_reg_date(Date s_reg_date) {
		this.s_reg_date = s_reg_date;
	}

	public Date getS_reg_date() {
		return s_reg_date;
	}

}
