package webmanager.database.abstractions;

import webmanager.interfaces.DatabaseObject;

/**
 * In cause to get safe access point to database via DatabaseController class it was generified.
 * But some of database operations doesn't use any operators, so use this class to bypass it.
 *
 * @author varpihovsky
 */
public class NullDatabaseObject implements DatabaseObject {
}
