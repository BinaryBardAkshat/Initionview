package androidx.core.location;

import android.location.GnssStatus;
import androidx.core.location.LocationManagerCompat;
import java.util.concurrent.Executor;

public final /* synthetic */ class LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.PreRGnssStatusTransport f$0;
    public final /* synthetic */ Executor f$1;
    public final /* synthetic */ GnssStatus f$2;

    public /* synthetic */ LocationManagerCompat$PreRGnssStatusTransport$$ExternalSyntheticLambda3(LocationManagerCompat.PreRGnssStatusTransport preRGnssStatusTransport, Executor executor, GnssStatus gnssStatus) {
        this.f$0 = preRGnssStatusTransport;
        this.f$1 = executor;
        this.f$2 = gnssStatus;
    }

    public final void run() {
        this.f$0.m13lambda$onSatelliteStatusChanged$3$androidxcorelocationLocationManagerCompat$PreRGnssStatusTransport(this.f$1, this.f$2);
    }
}
