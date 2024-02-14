package androidx.core.location;

import androidx.core.location.LocationManagerCompat;

public final /* synthetic */ class LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ LocationManagerCompat.LocationListenerTransport f$0;
    public final /* synthetic */ LocationListenerCompat f$1;
    public final /* synthetic */ int f$2;

    public /* synthetic */ LocationManagerCompat$LocationListenerTransport$$ExternalSyntheticLambda0(LocationManagerCompat.LocationListenerTransport locationListenerTransport, LocationListenerCompat locationListenerCompat, int i) {
        this.f$0 = locationListenerTransport;
        this.f$1 = locationListenerCompat;
        this.f$2 = i;
    }

    public final void run() {
        this.f$0.m6lambda$onFlushComplete$4$androidxcorelocationLocationManagerCompat$LocationListenerTransport(this.f$1, this.f$2);
    }
}
