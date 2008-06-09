package data_objects;

import data_objects.drivers.DriverDefinition;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyModule;
import org.jruby.RubyNumeric;
import org.jruby.RubyObject;
import org.jruby.RubyObjectAdapter;
import org.jruby.anno.JRubyClass;
import org.jruby.anno.JRubyMethod;
import org.jruby.javasupport.JavaEmbedUtils;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.builtin.IRubyObject;

import static data_objects.DataObjects.DATA_OBJECTS_MODULE_NAME;

/**
 * Reader Class
 *
 * @author alexbcoles
 */
@JRubyClass(name = "Reader")
public class Reader extends RubyObject {

    public final static String RUBY_CLASS_NAME = "Reader";
    private static RubyObjectAdapter api;
    private static DriverDefinition driver;
    private static String moduleName;
    private static String errorName;

    private final static ObjectAllocator READER_ALLOCATOR = new ObjectAllocator() {

        public IRubyObject allocate(Ruby runtime, RubyClass klass) {
            Reader instance = new Reader(runtime, klass);
            return instance;
        }
    };

    public static RubyClass createReaderClass(final Ruby runtime,
            final String moduleName, final String errorName,
            final DriverDefinition driverDefinition) {
        RubyModule doModule = runtime.getModule(DATA_OBJECTS_MODULE_NAME);
        RubyClass superClass = doModule.getClass(RUBY_CLASS_NAME);
        RubyModule driverModule = (RubyModule) doModule.getConstant(moduleName);
        RubyClass readerClass = driverModule.defineClassUnder(RUBY_CLASS_NAME,
                superClass, READER_ALLOCATOR);
        readerClass.defineAnnotatedMethods(Reader.class);
        Reader.api = JavaEmbedUtils.newObjectAdapter();
        Reader.driver = driverDefinition;
        Reader.moduleName = moduleName;
        Reader.errorName = errorName;
        return readerClass;
    }

    private Reader(Ruby runtime, RubyClass klass) {
        super(runtime, klass);
    }
    // -------------------------------------------------- DATAOBJECTS PUBLIC API

    // default initialize
    @JRubyMethod
    public static IRubyObject close(IRubyObject recv) {
        Ruby runtime = recv.getRuntime();
        IRubyObject reader = api.getInstanceVariable(recv, "@reader");

        if (!(reader == null || reader.isNil())) {

            ResultSet rs = (ResultSet) reader.dataGetStruct();
            try {
                Statement st = rs.getStatement();
                rs.close();
                rs = null;
                st.close();
                st = null;
            } catch (SQLException ex) {
                Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                reader = api.setInstanceVariable(recv, "@reader", runtime.getNil());
            }

            return runtime.getTrue();
        } else {
            return runtime.getFalse();
        }
    }

    /**
     * Moves the cursor forward.
     *
     * @param recv
     * @return
     */
    @JRubyMethod(name = "next!")
    public static IRubyObject next(IRubyObject recv) throws SQLException {
        Ruby runtime = recv.getRuntime();
        IRubyObject reader = api.getInstanceVariable(recv, "@reader");
        ResultSet rs = (ResultSet) reader.dataGetStruct();

        if (rs == null) {
            return runtime.getFalse();
        }

        IRubyObject field_types = api.getInstanceVariable(recv, "@types");
        IRubyObject field_count = api.getInstanceVariable(recv, "@field_count");
        RubyArray row = runtime.newArray();
        IRubyObject value;
        int fieldTypesCount = field_types.convertToArray().getLength();

        boolean hasNext = rs.next();
        api.setInstanceVariable(recv, "@state", runtime.newBoolean(hasNext));

        if (!hasNext) {
            return runtime.getNil();
        }

        for (int i = 0; i < RubyNumeric.fix2int(field_count.convertToInteger()); i++) {
            //if (fieldTypesCount == 0) {

            value = DataObjectsUtils.java_types_to_ruby_types(runtime, i, i, i, rs);

            //} else {
            //    value = rubyTypeCast(rs.getString(i));
            //}
            row.push_m(new IRubyObject[]{value});
        }

        api.setInstanceVariable(recv, "@values", row);
        return runtime.getTrue();
    }

    @JRubyMethod
    public static IRubyObject values(IRubyObject recv) {
        Ruby runtime = recv.getRuntime();
        IRubyObject state = api.getInstanceVariable(recv, "@state");

        if (state.isNil() || !state.isTrue()) {
            throw DataObjectsUtils.newDriverError(runtime, errorName, "Reader is not initialized");
        }
        IRubyObject values = api.getInstanceVariable(recv, "@values");
        return values;
    }

    @JRubyMethod
    public static IRubyObject fields(IRubyObject recv) {
        return api.getInstanceVariable(recv, "@fields");
    }
}