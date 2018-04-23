package Client.Remote;

import Shared.BaseService;
import Shared.UserService;

public interface RemoteServicesManager {
    UserService getUserService() throws Exception;
    BaseService getChildrenService() throws Exception;
    BaseService getMenuService() throws Exception;
    BaseService getDishService() throws Exception;

    void closeConnection() throws Exception;
}
