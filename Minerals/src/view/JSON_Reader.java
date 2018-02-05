package view;

/**
 * Insert class description here
 *
 * @author Lukas
 * @version Feb 2, 2018
 */
import java.awt.Rectangle;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import model.Mine;
import model.Minerals_SA;
import model.Node;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSON_Reader {

    JSONParser parser;
    Object fileObject;
    JSONObject jsonObject;

    public JSON_Reader() {
        this.parser = new JSONParser();
        this.fileObject = new Object();
        this.jsonObject = new JSONObject();
    }

    /**
     * Leer el archivo externo que llega como parámetro
     *
     * @param archivo elemento seleccionado por el usuario que contiene las
     * minas a cargar
     * @return Objeto minerals
     * @throws java.io.FileNotFoundException
     */
    public Minerals_SA leer(File archivo) throws FileNotFoundException, IOException {
        try {
            this.fileObject = this.parser.parse(new FileReader(archivo));

            //convert Object to JSONObject
            this.jsonObject = (JSONObject) fileObject;

            //Reading the String
            //String name = (String) jsonObject.get("Name");
            //Long age = (Long) jsonObject.get("Age");
            //Reading the array
            JSONArray infoMineros = (JSONArray) jsonObject.get("infoMineros");
            JSONArray infoMinas = (JSONArray) jsonObject.get("infoMinas");
            Object minas = jsonObject.get("minas");
            JSONObject jsonMinas = (JSONObject) minas;
            LinkedList<JSONArray> minasSeparadas = new LinkedList<>();
            if (jsonMinas.size() > 1) {
                for (int i = 0; i < jsonMinas.size(); i++) {
                    JSONArray arrayMinas = (JSONArray) jsonMinas.get(String.valueOf(i));
                    if (arrayMinas != null) {
                        minasSeparadas.add(arrayMinas);
                    }
                }
            } else if (jsonMinas.size() == 1) {
                JSONArray arrayMinas = (JSONArray) jsonMinas.get("0");
                if (arrayMinas != null) {
                    minasSeparadas.add(arrayMinas);
                }
            }

            System.out.println("Minas: " + minasSeparadas.size());

            Minerals_SA minerals;
            //Crea el objeto minerals
            minerals = createMines(infoMineros, infoMinas);
            //Agregar las minas al objeto minerals
            addMines(minasSeparadas, minerals);

            //Printing all the values
            //System.out.println("Name: " + name);
            //System.out.println("Age: " + age);
//            System.out.println("Info Mineros:");
//            leerArregloJSONMineros(infoMineros);
//            System.out.println("Info Minas:");
//            leerArregloJSONMinas(infoMinas);
//            if (minasSeparadas.size() >= 1) {
//                System.out.println("Minas");
//                for (JSONArray minaSeparada : minasSeparadas) {
//                    System.out.println("\t" + minasSeparadas.indexOf(minaSeparada) + ":");
//                    leerArregloJSONDetalleMina(minaSeparada);
//                }
//            }
            return minerals;
        } catch (ParseException e) {
            e.getMessage();
        }
        return null;
    }

    /**
     * Descripción del método.
     *
     * @param mineros descripción parámetro
     */
    public void leerArregloJSONMineros(JSONArray mineros) {
        for (Object info : mineros) {
            JSONObject jsonInfoMineros = (JSONObject) info;
            if (jsonInfoMineros.containsKey("totalMineros")) {
                System.out.println("\tTotal Mineros: " + jsonInfoMineros.get("totalMineros"));
            } else if (jsonInfoMineros.containsKey("totalMinerosOro")) {
                System.out.println("\tTotal Mineros Oro: " + jsonInfoMineros.get("totalMinerosOro"));
            } else if (jsonInfoMineros.containsKey("totalMinerosPlata")) {
                System.out.println("\tTotal Mineros Plata: " + jsonInfoMineros.get("totalMinerosPlata"));
            } else if (jsonInfoMineros.containsKey("totalMinerosCobre")) {
                System.out.println("\tTotal Mineros Cobre: " + jsonInfoMineros.get("totalMinerosCobre"));
            } else if (jsonInfoMineros.containsKey("totalMinerosComodin")) {
                System.out.println("\tTotal Mineros Comodín: " + jsonInfoMineros.get("totalMinerosComodin"));
            }
        }
    }

    /**
     * Descripción del método.
     *
     * @param minas descripción parámetro
     */
    public void leerArregloJSONMinas(JSONArray minas) {
        for (Object infoMina : minas) {
            JSONObject jsonInfoMina = (JSONObject) infoMina;
            if (jsonInfoMina.containsKey("capacidadCargaOro")) {
                System.out.println("\tCapacidad Carga Oro: " + jsonInfoMina.get("capacidadCargaOro"));
            } else if (jsonInfoMina.containsKey("capacidadCargaPlata")) {
                System.out.println("\tCapacidad Carga Plata: " + jsonInfoMina.get("capacidadCargaPlata"));
            } else if (jsonInfoMina.containsKey("capacidadCargaCobre")) {
                System.out.println("\tCapacidad Carga Cobre: " + jsonInfoMina.get("capacidadCargaCobre"));
            } else if (jsonInfoMina.containsKey("unidadCapacidadCarga")) {
                System.out.println("\tUnidad Capacidad Carga: " + jsonInfoMina.get("unidadCapacidadCarga"));
            } else if (jsonInfoMina.containsKey("gananciaOro")) {
                System.out.println("\tGanancia Oro: " + jsonInfoMina.get("gananciaOro"));
            } else if (jsonInfoMina.containsKey("gananciaPlata")) {
                System.out.println("\tGanancia Plata: " + jsonInfoMina.get("gananciaPlata"));
            } else if (jsonInfoMina.containsKey("gananciaCobre")) {
                System.out.println("\tGanancia Cobre: " + jsonInfoMina.get("gananciaCobre"));
            }
        }
    }

    /**
     * Descripción del método.
     *
     * @param detalleMina descripción parámetro
     */
    public void leerArregloJSONDetalleMina(JSONArray detalleMina) {
        for (Object mina : detalleMina) {
            JSONObject jsonMina = (JSONObject) mina;
            if (jsonMina.containsKey("tipoMineral")) {
                System.out.println("\tTipo Mineral: " + jsonMina.get("tipoMineral"));
            } else if (jsonMina.containsKey("capacidadMineros")) {
                System.out.println("\tCapacidad Mineros: " + jsonMina.get("capacidadMineros"));
            } else if (jsonMina.containsKey("capacidadDeposito")) {
                System.out.println("\tCapacidad Depósito: " + jsonMina.get("capacidadDeposito"));
            } else if (jsonMina.containsKey("unidadCapacidad")) {
                System.out.println("\tUnidad Capacidad: " + jsonMina.get("unidadCapacidad"));
            } else if (jsonMina.containsKey("tiempoExtraccion")) {
                System.out.println("\tTiempo Extracción: " + jsonMina.get("tiempoExtraccion"));
            } else if (jsonMina.containsKey("unidadTiempo")) {
                System.out.println("\tUnidad Tiempo: " + jsonMina.get("unidadTiempo"));
            } else if (jsonMina.containsKey("velocidadDesplazamiento")) {
                System.out.println("\tVelocidad Desplazamiento: " + jsonMina.get("velocidadDesplazamiento"));
            } else if (jsonMina.containsKey("unidadVelocidad")) {
                System.out.println("\tUnidad Velocidad: " + jsonMina.get("unidadVelocidad"));
            } else if (jsonMina.containsKey("ancho")) {
                System.out.println("\tAncho: " + jsonMina.get("ancho"));
            } else if (jsonMina.containsKey("largo")) {
                System.out.println("\tLargo: " + jsonMina.get("largo"));
            } else if (jsonMina.containsKey("entradaMina")) {
                JSONArray entradaMina = (JSONArray) jsonMina.get("entradaMina");
                System.out.println("\tEntrada Mina:");
                for (Object entrada : entradaMina) {
                    JSONObject jsonEntrada = (JSONObject) entrada;
                    if (jsonEntrada.containsKey("x")) {
                        System.out.println("\tX: " + jsonEntrada.get("x"));
                    } else if (jsonEntrada.containsKey("y")) {
                        System.out.println("\tY: " + jsonEntrada.get("y"));
                    }
                }
            } else if (jsonMina.containsKey("seccionesMina")) {
                System.out.println("\tSecciones Mina:");
                JSONObject objetoMina = (JSONObject) jsonMina.get("seccionesMina");
                for (int i = 0; i < objetoMina.size(); i++) {
                    JSONArray seccionMina = (JSONArray) objetoMina.get(String.valueOf(i));
                    System.out.println("\t" + i + ":");
                    for (int j = 0; j < seccionMina.size(); j++) {
                        JSONObject jsonSeccion = (JSONObject) seccionMina.get(j);
                        if (jsonSeccion.containsKey("tipo")) {
                            System.out.println("\tTipo: " + jsonSeccion.get("tipo"));
                        } else if (jsonSeccion.containsKey("x")) {
                            System.out.println("\tX: " + jsonSeccion.get("x"));
                        } else if (jsonSeccion.containsKey("y")) {
                            System.out.println("\tY: " + jsonSeccion.get("y"));
                        }
                    }
                }
            }
        }
    }

    private Minerals_SA createMines(JSONArray mineros, JSONArray minas) {
        Minerals_SA enterprise;
        int totalMiners = 0;
        int goldMiners = 0;
        int silverMiners = 0;
        int copperMiners = 0;
        int jokerMiners = 0;
        int capacityGold = 0;
        int capacitySilver = 0;
        int capacityCopper = 0;
        String chargeUnit = "";
        int goldGain = 0;
        int silverGain = 0;
        int copperGain = 0;
        for (Object info : mineros) {
            JSONObject jsonInfoMineros = (JSONObject) info;
            if (jsonInfoMineros.containsKey("totalMineros")) {
                totalMiners = Integer.parseInt(String.valueOf(jsonInfoMineros.get("totalMineros")));
            } else if (jsonInfoMineros.containsKey("totalMinerosOro")) {
                goldMiners = Integer.parseInt(String.valueOf(jsonInfoMineros.get("totalMinerosOro")));
            } else if (jsonInfoMineros.containsKey("totalMinerosPlata")) {
                silverMiners = Integer.parseInt(String.valueOf(jsonInfoMineros.get("totalMinerosPlata")));
            } else if (jsonInfoMineros.containsKey("totalMinerosCobre")) {
                copperMiners = Integer.parseInt(String.valueOf(jsonInfoMineros.get("totalMinerosCobre")));
            } else if (jsonInfoMineros.containsKey("totalMinerosComodin")) {
                jokerMiners = Integer.parseInt(String.valueOf(jsonInfoMineros.get("totalMinerosComodin")));
            }
        }
        for (Object infoMina : minas) {
            JSONObject jsonInfoMina = (JSONObject) infoMina;
            if (jsonInfoMina.containsKey("capacidadCargaOro")) {
                capacityGold = Integer.parseInt(String.valueOf(jsonInfoMina.get("capacidadCargaOro")));
            } else if (jsonInfoMina.containsKey("capacidadCargaPlata")) {
                capacitySilver = Integer.parseInt(String.valueOf(jsonInfoMina.get("capacidadCargaPlata")));
            } else if (jsonInfoMina.containsKey("capacidadCargaCobre")) {
                capacityCopper = Integer.parseInt(String.valueOf(jsonInfoMina.get("capacidadCargaCobre")));
            } else if (jsonInfoMina.containsKey("unidadCapacidadCarga")) {
                chargeUnit = String.valueOf(jsonInfoMina.get("unidadCapacidadCarga"));
            } else if (jsonInfoMina.containsKey("gananciaOro")) {
                goldGain = Integer.parseInt(String.valueOf(jsonInfoMina.get("gananciaOro")));
            } else if (jsonInfoMina.containsKey("gananciaPlata")) {
                silverGain = Integer.parseInt(String.valueOf(jsonInfoMina.get("gananciaPlata")));
            } else if (jsonInfoMina.containsKey("gananciaCobre")) {
                copperGain = Integer.parseInt(String.valueOf(jsonInfoMina.get("gananciaCobre")));
            }
        }
        enterprise = new Minerals_SA(totalMiners, goldMiners, silverMiners, copperMiners, jokerMiners, capacityGold, capacitySilver, capacityCopper, chargeUnit, goldGain, silverGain, copperGain);

        return enterprise;
    }

    /**
     * Descripción del método.
     *
     * @param minasSeparadas descripción parámetro
     * @param entreprise descripción parámetro
     */
    public void addMines(LinkedList<JSONArray> minasSeparadas, Minerals_SA entreprise) {
        if (minasSeparadas.size() >= 1) {
            for (JSONArray minaSeparada : minasSeparadas) {
                String mineralType = "nulo";
                int minersCapacity = 0;
                int depositCapacity = 0;
                double timeExtraction = 0.0;
                String timeUnit = "nulo";
                int displacementSpeed = 0;
                String speedUnit = "nulo";
                int widthMine = 0;
                int lengthMine = 0;
                //System.out.println("\t" + minasSeparadas.indexOf(minaSeparada) + ":");
                for (Object mina : minaSeparada) {
                    JSONObject jsonMina = (JSONObject) mina;
                    if (jsonMina.containsKey("tipoMineral")) {
                        mineralType = String.valueOf(jsonMina.get("tipoMineral"));
                    } else if (jsonMina.containsKey("capacidadMineros")) {
                        minersCapacity = Integer.parseInt(String.valueOf(jsonMina.get("capacidadMineros")));
                    } else if (jsonMina.containsKey("capacidadDeposito")) {
                        depositCapacity = Integer.parseInt(String.valueOf(jsonMina.get("capacidadDeposito")));
                    } else if (jsonMina.containsKey("tiempoExtraccion")) {
                        timeExtraction = Double.parseDouble(String.valueOf(jsonMina.get("tiempoExtraccion")));
                    } else if (jsonMina.containsKey("unidadTiempo")) {
                        timeUnit = String.valueOf(jsonMina.get("unidadTiempo"));
                    } else if (jsonMina.containsKey("velocidadDesplazamiento")) {
                        displacementSpeed = Integer.parseInt(String.valueOf(jsonMina.get("velocidadDesplazamiento")));
                    } else if (jsonMina.containsKey("unidadVelocidad")) {
                        speedUnit = String.valueOf(jsonMina.get("unidadVelocidad"));
                    } else if (jsonMina.containsKey("ancho")) {
                        widthMine = Integer.parseInt(String.valueOf(jsonMina.get("ancho")));
                    } else if (jsonMina.containsKey("largo")) {
                        lengthMine = Integer.parseInt(String.valueOf(jsonMina.get("largo")));
                    }
//                    System.out.println("Mineral: " + !mineralType.equalsIgnoreCase("nulo"));
//                    System.out.println("CapMineros: " + String.valueOf(minersCapacity != 0));
//                    System.out.println("CapDeposito: " + String.valueOf(depositCapacity != 0));
//                    System.out.println("Tiempo:" + String.valueOf(timeExtraction != 0));
//                    System.out.println("UnidadT:" + !timeUnit.equalsIgnoreCase("nulo"));
//                    System.out.println("Velocidad:" + String.valueOf(displacementSpeed != 0));
//                    System.out.println("UnidadV:" + !speedUnit.equalsIgnoreCase("nulo"));
//                    System.out.println("Ancho:" + String.valueOf(widthMine != 0));
//                    System.out.println("largo:" + String.valueOf(lengthMine != 0));

                    if (!mineralType.equalsIgnoreCase("nulo") && minersCapacity != 0 && depositCapacity != 0 && timeExtraction != 0 && !timeUnit.equalsIgnoreCase("nulo") && displacementSpeed != 0 && !speedUnit.equalsIgnoreCase("nulo") && widthMine != 0 && lengthMine != 0) {
                        Mine current = new Mine(mineralType, minersCapacity, depositCapacity, timeExtraction, timeUnit, displacementSpeed, speedUnit, widthMine, lengthMine);
//                        Node currentNode;
//                        for (int i = 0; i < lengthMine; i++) {
//                            for (int j = 0; j < widthMine; j++) {
//                                Rectangle space = new Rectangle();
//                                currentNode = new Node(space, i, j);
//                                current.addMapPart(currentNode);
//                            }
//                        }
                        entreprise.getMines().add(current);
                        System.out.println("Mina agregada:" + entreprise.getMines().size());
                        int mineIndex = minasSeparadas.indexOf(minaSeparada);
                        addMineSectors(minaSeparada, entreprise, mineIndex);
                        mineralType = "nulo";
                        minersCapacity = 0;
                        depositCapacity = 0;
                        timeExtraction = 0.0;
                        timeUnit = "nulo";
                        displacementSpeed = 0;
                        speedUnit = "nulo";
                        widthMine = 0;
                        lengthMine = 0;

                    } //else {
//                        System.out.println(mineralType + minersCapacity + depositCapacity + timeExtraction + timeUnit + displacementSpeed + speedUnit + widthMine + lengthMine);
//                    }
                }
            }
        }
    }

    /**
     * Descripción del método.
     *
     * @param jsonMinaCompleta descripción parámetro
     * @param entreprise
     * @param mineIndex
     */
    public void addMineSectors(JSONArray jsonMinaCompleta, Minerals_SA entreprise, int mineIndex) {
        for (Object mina : jsonMinaCompleta) {
            JSONObject jsonMina = (JSONObject) mina;
            if (jsonMina.containsKey("entradaMina")) {
                JSONArray entradaMina = (JSONArray) jsonMina.get("entradaMina");
                //System.out.println("\tEntrada Mina:");
                for (Object entrada : entradaMina) {
                    JSONObject jsonEntrada = (JSONObject) entrada;
                    int xExit = 0;
                    int yExit = 0;
                    if (jsonEntrada.containsKey("x")) {
                        xExit = Integer.parseInt(String.valueOf(jsonEntrada.get("x")));
                    } else if (jsonEntrada.containsKey("y")) {
                        yExit = Integer.parseInt(String.valueOf(jsonEntrada.get("y")));
                    }
                    if (xExit != 0 && yExit != 0) {
                        entreprise.getMines().get(mineIndex).setExitWithPosition(xExit, yExit);
                    }
                }
            } else if (jsonMina.containsKey("seccionesMina")) {
                //System.out.println("\tSecciones Mina:");
                int xCurrent = -1;
                int yCurrent = -1;
                String type = "";
                JSONObject objetoMina = (JSONObject) jsonMina.get("seccionesMina");
                String mineralType = entreprise.getMines().get(mineIndex).getMineral();
                for (int i = 0; i < objetoMina.size(); i++) {
                    JSONArray seccionMina = (JSONArray) objetoMina.get(String.valueOf(i));
                    //System.out.println("\t" + i + ":");
                    for (int j = 0; j < seccionMina.size(); j++) {
                        JSONObject jsonSeccion = (JSONObject) seccionMina.get(j);
                        if (jsonSeccion.containsKey("tipo")) {
                            type = String.valueOf(jsonSeccion.get("tipo"));
                        } else if (jsonSeccion.containsKey("x")) {
                            xCurrent = Integer.parseInt(String.valueOf(jsonSeccion.get("x")));
                        } else if (jsonSeccion.containsKey("y")) {
                            yCurrent = Integer.parseInt(String.valueOf(jsonSeccion.get("y")));
                        }
                        if (xCurrent != -1 && yCurrent != -1 && !type.equals("")) {
                            Node currentNode = entreprise.getMines().get(mineIndex).getElementinPosition(xCurrent, yCurrent);
                            if (currentNode != null) {
                                if (type.equalsIgnoreCase("T")) {
                                    currentNode.setCategory(3);
                                } else if (type.equalsIgnoreCase("D")) {
                                    currentNode.setCategory(2);
                                }
                                entreprise.getMines().get(mineIndex).addDeposit(currentNode, mineralType, yCurrent);
                                xCurrent = -1;
                                yCurrent = -1;
                                type = "";
                            }

                        }
                    }
                }
            }
        }

    }

}
