package gatos;

import com.google.gson.Gson;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class GatosService {

    static public void verGatos() {

        // Request (GET )
        String elJson = null;
        try {
            //Crear la solicitud & Reagrupa la informacion obtenida obtniendo el contenido de la API
            Content content = Request.Get("https://api.thecatapi.com/v1/images/search").execute().returnContent();
            System.out.println(content);
            elJson = content.asString();
        } catch (IOException e) {
            System.out.println(e);
        }

        //Elimina los corchetes que se obtuvieron en el Json que surgio del request.
        elJson = elJson.substring(1, elJson.length() - 1);

        System.out.println(elJson);

        //Crear un objeto del tipo Gson para poder extraer los argumentos
        Gson gson = new Gson();
        //Convierte el Gson que contiene los datos que obtuvo de la API, a un objeto del tipo Gatos para manipular los datos obtenidos
        Gatos gatos = gson.fromJson(elJson, Gatos.class);

        //Redimensionar en caso de necesitar.
        Image imagen = null;
        //La clase paint recibe una imagen de tipo Icon (objeto del tipo ImageIcon)
        ImageIcon fondoGato = null;

        try{
            //Obtenemos la url del objeto gato. Recordar que esta clase se agregaron nuevos atributos por medio de Gson
            URL url = new URL(gatos.getUrl());
            //Cargamos la url al objeto imagen.
            imagen = ImageIO.read(url);
            //Asignamos el objeto ImageIO al objeto ImageIcon
            fondoGato = new ImageIcon(imagen);

            if (fondoGato.getIconWidth() > 800){
                //Redimensionamos la imagen a menor o igual 800 pixeles
                Image fondo = fondoGato.getImage();
                Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                fondoGato = new ImageIcon(modificada);
                gatos.setWidth(800);
                gatos.setHeight(600);
            }



        }catch(IOException e){
            System.out.println(e);
        }


        System.out.println(gatos.getUrl());
        System.out.println(gatos.getWidth());
        System.out.println(gatos.getHeight());
        System.out.println(gatos.getApikey());

        //Ejecutamos funcion para que muestre los datos procesados
        Menu.gatos(gatos, fondoGato);
    }

    static public void setFavoritoGatos(Gatos gatof){
// Request (POST )

        try {

            //Crear la solicitud, agregando los headers, el cuerpo, agrupando la solicitud y retornando el contenido
            Content content = Request.Post("https://api.thecatapi.com/v1/favourites")
                    .addHeader("Content-Type", "application/json")
                    .addHeader("x-api-key", gatof.getApikey())
                    .bodyString("{\"image_id\": \""+gatof.getId()+"\"}", ContentType.APPLICATION_JSON)
                    .execute().returnContent();

            System.out.println(content);
        }
        catch (IOException e) {
            System.out.println(e);
        }

    }

    public static void verFavoritos(String apikey, int min) {

        String elJson = null;
        try {

            //Crear la solicitud & Reagrupa la informacion obtenida obtniendo el contenido de la API pero esta ves la seccion favoritos
            Content content = Request.Get("https://api.thecatapi.com/v1/favourites")
                    .addHeader("x-api-key", apikey) //Agregamos la apikey para ingresar a nuestros favoritos
                    .addHeader("Content-Type", "application/octet-stream")
                    .execute().returnContent();

            System.out.println(content);
            elJson = content.asString();
            System.out.println(elJson);
        }
        catch (IOException e) { System.out.println(e); }

        //Crear un objeto del tipo Gson para poder extraer los argumentos. Para este caso no eliminamos los corchetes ya que es un Array de objetos
        Gson gson = new Gson();
        //Convierte el Gson que contiene los datos que obtuvo de la API, a un objeto del tipo GatosFavoritos de forma Array para manipular los datos obtenidos
        GatosFavoritos[] gatosArray = gson.fromJson(elJson, GatosFavoritos[].class);

        int max = gatosArray.length;

        if(max > 0){
            for(int i = min; i < max; i++) {
                //Asignamos el gato favorito a una variable temporal
                GatosFavoritos gatofav = gatosArray[i];
                //Redimensionar en caso de necesitar.
                Image imagen = null;
                //La clase paint recibe una imagen de tipo Icon (objeto del tipo ImageIcon)
                ImageIcon fondoGato = null;

                try {
                    //Obtenemos la url del objeto gato. Recordar que esta clase se agregaron nuevos atributos por medio de Gson
                    URL url = new URL(gatofav.image.getUrl());
                    //Cargamos la url al objeto imagen.
                    imagen = ImageIO.read(url);
                    //Asignamos el objeto ImageIO al objeto ImageIcon
                    fondoGato = new ImageIcon(imagen);

                    if (fondoGato.getIconWidth() > 800) {
                        //Redimensionamos la imagen a menor o igual 800 pixeles
                        Image fondo = fondoGato.getImage();
                        Image modificada = fondo.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
                        fondoGato = new ImageIcon(modificada);
                        gatofav.image.setWidth(800);
                        gatofav.image.setHeight(600);
                    }
                } catch (IOException e) {
                    System.out.println(e);
                }

                System.out.println(gatofav.image.getUrl());
                System.out.println(gatofav.image.getWidth());
                System.out.println(gatofav.image.getHeight());
                System.out.println(gatofav.getApikey());

                //Ejecutamos funcion para que muestre los datos procesados
                Menu.favoritos(gatofav, fondoGato, min, max);
            }
        }
    }

    public static void borrarFavorito(String apikey, String idFavorito){
        // Request (DELETE )

        try {

            //Crear la solicitud, agregando los headers, el cuerpo, agrupando la solicitud y retornando el contenido
            Content content = Request.Delete("https://api.thecatapi.com/v1/favourites/" + idFavorito )
                    .addHeader("x-api-key", apikey)
                    .addHeader("Content-Type", "application/octet-stream")
                    .execute().returnContent();

            System.out.println(content);
        }
        catch (IOException e) {
            System.out.println(e);
        }
    }
}
