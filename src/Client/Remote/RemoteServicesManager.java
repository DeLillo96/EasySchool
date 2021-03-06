package Client.Remote;

import Shared.*;

/**
 * Interface with methods implemented by ServicesManagers, used to associate a specific service when it's get() function
 * is invoked
 */
public interface RemoteServicesManager {
    UserService getUserService() throws Exception;

    BaseService getChildrenService() throws Exception;

    BaseService getAdultService() throws Exception;

    BaseService getSupplierService() throws Exception;

    BaseService getPediatricianService() throws Exception;

    BaseService getAlimentService() throws Exception;

    BaseService getBusService() throws Exception;

    BaseService getCalendarService() throws Exception;

    BaseService getDayTripService() throws Exception;

    EatingDisorderService getEatingDisorderService() throws Exception;

    BaseService getMenuService() throws Exception;

    RelationService getRecipesService() throws Exception;

    BaseService getPlaceService() throws Exception;

    RelationService getParentService() throws Exception;

    RelationService getSupplyingService() throws Exception;

    RelationService getChildPediatricianService() throws Exception;

    RelationService getTripPlaceService() throws Exception;

    RelationService getDailyDishService() throws Exception;

    BaseService getDailyTripService() throws Exception;

    RelationService getChildInTripService() throws Exception;

    BaseService getDishService() throws Exception;

    BaseService getFirstDishService() throws Exception;

    BaseService getSecondDishService() throws Exception;

    BaseService getSideDishService() throws Exception;

    BaseService getSweetDishService() throws Exception;

    BaseService getStaffService() throws Exception;

    RelationService getBusTripService() throws Exception;

    RelationService getPlaceInTripService() throws Exception;

    TernaryRelationService getChildInVehicleService() throws Exception;

    void closeConnection() throws Exception;
}
