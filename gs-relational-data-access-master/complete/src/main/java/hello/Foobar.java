package hello;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Foobar {
	private long id;
	private String col1;
	private String col2;
	private Date sRegDate;

	///// for define RowMapper.
	public Foobar() {
	}

	///// for define RowMapper.
	public Foobar(long id, String col1, String col2, Date sRegDate) {
		this.id = id;
		this.col1 = col1;
		this.col2 = col2;
		this.sRegDate = sRegDate;
	}

	///// for define RowMapper.
	public Foobar(long id, String col1, String col2, Timestamp sRegDate) {
		this(id, col1, col2, new Date(sRegDate.getTime()));
	}

	@Override
	public String toString() {
		return "Foobar [id=" + id + ", col1=" + col1 + ", col2=" + col2 + ", sRegDate=" + date2String(sRegDate) + "]";
	}

	public String date2String(Date date) {
		return date == null ? "(null)" : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(date);
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

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public Date getsRegDate() {
		return sRegDate;
	}

	public void setsRegDate(Date sRegDate) {
		this.sRegDate = sRegDate;
	}


}
