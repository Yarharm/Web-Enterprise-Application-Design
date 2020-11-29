package business_layer;

import configuration.ConfigDriver;
import helpers.Utils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class UserManagerFactory {
    private UserManagerFactory(){}

    private static UserManagerFactory factoryInstance = null;

    public static UserManagerFactory getInstance() {
        if(factoryInstance == null){
            synchronized(UserManagerFactory.class) {
                if(factoryInstance == null)
                    factoryInstance = new UserManagerFactory();
            }
        }
        return factoryInstance;
    }

    public IUserManager createUserManager(String userFileName) {
        IUserManager userManager = null;
        try {
            String userFilePath = Utils.buildTargetFilePath(userFileName);
            Class<?> cl = Class.forName(ConfigDriver.getUserManagerClassName());
            Class<?>[] type = { String.class };
            Constructor<?> cons = cl.getConstructor(type);
            Object[] obj = { userFilePath };
            userManager = (IUserManager) cons.newInstance(obj);
        } catch(IOException e ) {
            System.err.println("Could not load user file");
            System.exit(1);
        } catch(ClassNotFoundException e) {
            System.err.println("Could not load User Manager class");
            System.exit(1);
        }  catch (NoSuchMethodException e) {
            System.err.println("Could not load User Manager constructor");
            System.exit(1);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
            System.err.println("Could not create User Manager instance");
            System.exit(1);
        }
        return userManager;
    }
}
