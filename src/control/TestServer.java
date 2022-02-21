package control;

import model.List;
import view.Server.InteractionPanelHandlerServer;

/**Aus Gründen der Vereinfachung gibt es eine "Verzahnung" (gegenseitige Kennt-Beziehung --> Assoziation) zwischen TestServer und InteractionsPanelHandlerServer.
 *  Im fertigen Programm existiert jeweils ein Objekt. Beide Objekte kennen sich gegenseitig.
 * Created by AOS on 18.09.2017.
 * Updated by AOS on 13.02.2022.
 */
public class TestServer extends Server{

    private InteractionPanelHandlerServer panelHandler;
    private List<String> clients;

    public TestServer(int pPort, InteractionPanelHandlerServer panel) {
        super(pPort);
        clients = new List<>();
        this.panelHandler = panel;
        //TODO 02 Falls der Server offen ist, werden die Knöpfe im Panel angeschaltet: buttonsSwitch aufrufen. Ansonsten erfolgt eine Ausgabe, dass es ein Problem beim Starten gab.
        if (isOpen()){
            panel.buttonSwitch();
        }else panelHandler.showErrorMessage("PROOOOBLEEEEM");
    }

    @Override
    public void processNewConnection(String pClientIP, int pClientPort) {
        clients.append(pClientIP+":"+pClientPort); //TODO 03a Erläutern Sie, was hier passiert
        //In die Liste mit Clients wird ein Neuer Client als String hinzugefuegt
        panelHandler.displayNewConnection(pClientIP,pClientPort);
    }

    @Override
    public void processMessage(String pClientIP, int pClientPort, String pMessage) {
        panelHandler.showProcessMessageContent(pClientIP,pClientPort,pMessage); //TODO 03b Erläutern Sie, was hier passiert.
        //Wenn eine neue Nachricht ankommt, dann wird diese dargestellt
    }

    @Override
    public void processClosingConnection(String pClientIP, int pClientPort) {
        //TODO 03c Erläutern Sie, was hier passiert.
        //Wenn eine Verbindung abbricht, dann wird der Client aus der Liste mit Clients entfernt

        clients.toFirst();
        while (clients.hasAccess()){
            if(clients.getContent().toString().contains(pClientIP)){
                clients.remove();
            }else{
                clients.next();
            }
        }

        panelHandler.displayClosingConnection(pClientIP, pClientPort);
    }

    /**
     * Sobald der Server geschlossen wird, werden die meisten Knöpfe wieder deaktiviert.
     */
    @Override
    public void close(){
        super.close();
        panelHandler.buttonSwitch();
    }

	/**
     * Jeder Client wird in der Liste clients geführt.
     * Diese Methode gibt eine Darstellung aller Clients in der Form "IP:Port" als String-Array zurück.
     * @return String-Array mit Client-Informationen
     */
    public String[] getClients(){
        //TODO 04 Ein Hoch auf die Standard-Listen/Array-Aufgaben! Bitte umsetzen.
        clients.toFirst();
        int count = 0;
        while (clients.hasAccess()){
            clients.next();
            count++;
        }
        String[] out = new String[count];
        clients.toFirst();
        count = 0;
        while (clients.hasAccess()){
            out[count] = clients.getContent();
            clients.next();
            count++;
        }
        return out;
    }
}
