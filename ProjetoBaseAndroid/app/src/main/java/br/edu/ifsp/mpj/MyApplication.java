package br.edu.ifsp.mpj;

import android.util.Log;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarDb;

import br.edu.ifsp.mpj.entity.User;

/**
 * Created by falvojr on 5/11/17.
 */
public class MyApplication extends SugarApp {

    private static final String TAG = MyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        // create table if not exists
        SchemaGenerator schemaGenerator = new SchemaGenerator(this);
        schemaGenerator.createDatabase(new SugarDb(this).getDB());

        final long count = User.count(User.class);
        if (count == 0) {
            User superUser = new User();
            superUser.setEmail("root");
            superUser.setPassword("root");
            superUser.save();
        } else if (count == -1) {
            Log.w(TAG, "Tabela User não existe!");
        }
        // Links Uteis:
        // http://stackoverflow.com/a/28339178/3072570
    }
}
