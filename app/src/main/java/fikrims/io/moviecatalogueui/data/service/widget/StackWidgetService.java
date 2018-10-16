package fikrims.io.moviecatalogueui.data.service.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import fikrims.io.moviecatalogueui.feature.widget.FavoriteStackRemoteViewsFactory;


public class StackWidgetService extends RemoteViewsService  {

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new FavoriteStackRemoteViewsFactory(this.getApplicationContext(), intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}
