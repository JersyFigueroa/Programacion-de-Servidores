package Cloud_Computing;

public class FogNode {
    private final CloudServer CLOUDSERVER;
    private int alertCount;
    private int id;

    private List<SensorData> buffer = new ArrayList<>();

    public FogNode (CloudServer cloudServer, int id){
        this.CLOUDSERVER = cloudServer;
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void processData(SensorData data){
        
        System.out.println("[FOG] Dato recibido: " + data);

        if(data.getTEMPERATURE()>30){
            alertCount++;
            System.out.println("[FOG] ALERTA: temperatura alta");

             if(alertCount == 20){
            System.out.println("LIMITE DE 20 ALERTAS ALCANZADO");
        }
            
        } else
            System.out.println("[FOG] Temperatura normal");
    } 
    
    buffer.add(data);

        if(buffer.size() == 5){
            sendToCloud();
        }
    }

    private void sendToCloud(){
        System.out.println("Enviando paquete de 5 datos al CLOUD");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(SensorData d : buffer){
            CLOUDSERVER.saveData(d);
        }

        buffer.clear();
    }

    public int getAlertCount(){
        return alertCount;
    }

}
