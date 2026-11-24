import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;

import javax.swing.*;
import javax.swing.event.MouseInputListener;
public class App {
    

 
    public static void main(String[] args) {
       
        TileFactoryInfo info = new OSMTileFactoryInfo("OpenStreetMap", "https://tile.openstreetmap.org");
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        tileFactory.setUserAgent("SistemaDeliveryLiruCisa/1.0 (sr202532135@alm.buap.mx)");
        tileFactory.setThreadPoolSize(8);
        JXMapViewer mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);

        // 2. Centrar el mapa (Ejemplo: Zócalo de Puebla)
        GeoPosition centro = new GeoPosition(19.0414, -98.2063);
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(centro);

        // 3. Habilitar interacción con el mouse (arrastrar con clic izquierdo y zoom con la rueda)
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCenter(mapViewer));

        // 4. Crear y mostrar la ventana Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Liru Cisa - Prueba de Mapa Interactivo");
            frame.getContentPane().add(mapViewer);
            frame.setSize(900, 650);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

