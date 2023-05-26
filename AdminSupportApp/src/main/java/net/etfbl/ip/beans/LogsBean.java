package net.etfbl.ip.beans;

import java.io.Serializable;
import java.util.List;

import net.etfbl.ip.dao.LogDAO;
import net.etfbl.ip.dto.Log;

public class LogsBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -565581126733987301L;

	public LogsBean() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Log> getLogs() {
		return LogDAO.selectAllLogs();
	}

}
