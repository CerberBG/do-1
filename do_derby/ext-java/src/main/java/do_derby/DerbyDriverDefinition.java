package do_derby;

import data_objects.drivers.AbstractDriverDefinition;

public class DerbyDriverDefinition extends AbstractDriverDefinition {

    public boolean supportsJdbcGeneratedKeys()
    {
        return true;
    }

}