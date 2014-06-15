package tw.edu.chu.csie.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BusService {

    private String route;
    private boolean back;
    private Date time;
    private int week;
    private Date startDate, endDate;
    private String company;
    
    public BusService(String _route, boolean _back, 
            Date _time, int _week, 
            Date _startDate, Date _endDate, 
            String _company) {
        
        route = _route;
        back = _back;
        time = _time;
        week = _week;
        startDate = _startDate;
        endDate = _endDate;
        company = _company;
        
    }
    
    public BusService(String _route, boolean _back, 
            String _time, int _week, 
            String _startDate, String _endDate, 
            String _company) throws ParseException {
        
        this(_route, _back, 
                new SimpleDateFormat("HH:mm").parse(_time), 
                _week, 
                new SimpleDateFormat("yyyy/MM/dd").parse(_startDate), 
                new SimpleDateFormat("yyyy/MM/dd").parse(_endDate), 
                _company);
    }
    
    public String getRoute() {
        return route;
    }
    
    public boolean isBack() {
        return back;
    }
    
    public Date getTime() {
        return time;
    }
    
    public int getWeek() {
        return week;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public String getCompany() {
        return company;
    }
}
