package fr.esgi.zonevaluator.business;

public class GeneratePdfMessageQueue {

    private Float latitude;
    private Float longitude;
    private Float rayon;
    private Long idPdf;

    public GeneratePdfMessageQueue() {
        super();
    }

    public GeneratePdfMessageQueue(Float latitude, Float longitude, Float rayon, Long idPdf) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.rayon = rayon;
        this.idPdf = idPdf;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getRayon() {
        return rayon;
    }

    public void setRayon(Float rayon) {
        this.rayon = rayon;
    }

    public Long getIdPdf() {
        return idPdf;
    }

    public void setIdPdf(Long idPdf) {
        this.idPdf = idPdf;
    }
}
