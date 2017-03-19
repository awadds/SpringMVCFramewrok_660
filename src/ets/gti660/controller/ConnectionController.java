package ets.gti660.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.catalina.util.ServerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ets.gti660.model.Connection;
import ets.gti660.template.ConnectionJDBCTemplate;

@Controller
public class ConnectionController {
	private Connection connection = Connection.getInstance();

	@Autowired
	private ConnectionJDBCTemplate connectionJDBCTemplate;

	@RequestMapping(value = "/connection", method = RequestMethod.GET)
	public String connection(ModelMap model) {

		// Get the current time and convert to string
		Date currentTime = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String currentTimeStr = sdf.format(currentTime);
		String statusDB = "";
		String lastCheckedDB = "";
		String urlDB = "";
		String usernameDB = "";

		// Check if connection is up
		Boolean isConClosed = connectionJDBCTemplate.testConnection();

		// If isClosed = false then Active = True
		connection.setActive(!isConClosed);

		// Get the status from connection object
		statusDB = connection.getStatus();
		/*System.out.println("statusDB = " + statusDB);*/

		// Get server url and username if connection is up
		String[] serverInfo = null;

		if (!statusDB.equals("Closed")) {
			serverInfo = connectionJDBCTemplate.getServerInfo();
			urlDB = serverInfo[0];
			usernameDB = serverInfo[1];
			/*System.out.println("urlDB = " + urlDB);
			System.out.println("usernameDB = " + usernameDB);*/
		}

		// Set lastChecked to the current time
		connection.setLastChecked(currentTimeStr);
		// Add the time to the result string
		lastCheckedDB = connection.getLastChecked();

		// Add attributes to the model
		model.addAttribute("statusDB", statusDB);
		model.addAttribute("urlDB", urlDB);
		model.addAttribute("usernameDB", usernameDB);
		model.addAttribute("lastCheckedDB", lastCheckedDB);

		return "connection";
	}
}
