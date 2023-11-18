package nextu.ilalic.jevendstout.service;

public interface BanqueService {
    /**
     * Effectue le paiement à l'api de la banque
     * @return boolean si le paiement s'est bien déroulé
     */
    boolean payer();
}
