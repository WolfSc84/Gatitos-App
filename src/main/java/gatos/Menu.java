package gatos;

import javax.swing.*;

public class Menu {

    static public void showMenu() {

        int opcionMenu = 0;
        //Creamos el string que mostrara las opciones en el menu grafico
        String[] botones = {"1. Ver gatos.", "2. Ver favoritos.","3. Salir"};

        do {
            //Cargamos las opciones al objeto grafico de tipo JoptionPane, indicando los atributos necesarios
            String opcionCD = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Menu Principal", JOptionPane.INFORMATION_MESSAGE, null, botones, botones[0]);

            //Revisamos la opcion seleccionada en el objeto grafico JoptionPane
            for (int i = 0; i < botones.length; i++) {
                if (opcionCD.equals(botones[i])) {
                    opcionMenu = i;
                }
            }

            //Evaluamos la opcion seleccionada para ejecutar la accion correspondiente
            switch (opcionMenu) {
                case 0:
                    GatosService.verGatos();
                    break;
                case 1:
                    Gatos gatof = new Gatos();
                    GatosService.verFavoritos(gatof.getApikey(), 0);
                    break;
                case 2:
                    break;
                default:
                    break;
            }

        } while (opcionMenu != 1);
    }

    static public void gatos(Gatos gatos, ImageIcon fondoGato){

        //Creamos el string que mostrara las opciones en el menu grafico
        String[] botonesg = {"Ver otra imagen", "Favorito", "Volver"};
        //Cargamos las opciones al objeto grafico de tipo JoptionPane, indicando los atributos necesarios
        String opcionselec = (String) JOptionPane.showInputDialog(null, "Gatitos Java", "Gatito Id: " + gatos.getId(), JOptionPane.INFORMATION_MESSAGE, fondoGato, botonesg, botonesg[0]);

        int select = 0;

        //Evaluamos la opcion seleccionada para ejecutar la accion correspondiente
        for(int j=0; j < botonesg.length ; j++){
            if( opcionselec.equals(botonesg[j]) ) {
                select = j;
            }
        }
        //Evaluamos la opcion seleccionada para ejecutar la accion correspondiente
        switch ( select ){
            case 0:
                GatosService.verGatos();
                break;
            case 1:
                GatosService.setFavoritoGatos(gatos);
                GatosService.verGatos();
                break;
            case 2:
                showMenu();
                break;
        }
    }

    static public void favoritos(GatosFavoritos gatof, ImageIcon fondoGato, int min, int max ){
        //Creamos el string que mostrara las opciones en el menu grafico
        String[] botonesg = {"Ver otra imagen favorita", "Eliminar esta imagen favorita", "Volver"};
        //Cargamos las opciones al objeto grafico de tipo JoptionPane, indicando los atributos necesarios
        String opcionselec = (String) JOptionPane.showInputDialog(null, "Gatitos Favoritos Java", "Gatito Id: " + gatof.getId(), JOptionPane.INFORMATION_MESSAGE, fondoGato, botonesg, botonesg[0]);

        int select = 0;

        //Evaluamos la opcion seleccionada para ejecutar la accion correspondiente
        for(int j=0; j < botonesg.length ; j++){
            if( opcionselec.equals(botonesg[j]) ) {
                select = j;
            }
        }
        //Evaluamos la opcion seleccionada para ejecutar la accion correspondiente
        switch ( select ){
            case 0:
                if((min+1) == max){
                    min = 0;
                }else{
                    min++;
                }
                //Redefinimos el minimo del array y llamamos de nuevo favoritos
                GatosService.verFavoritos(gatof.getApikey(), min);
                break;
            case 1:
                GatosService.borrarFavorito(gatof.getApikey(), gatof.getId());
                if((min+1) == max){
                    min = 0;
                }else{
                    min++;
                }
                GatosService.verFavoritos(gatof.getApikey(), min);
                break;
            case 2:
                showMenu();
                break;
            default:
                break;
        }
    }





}
