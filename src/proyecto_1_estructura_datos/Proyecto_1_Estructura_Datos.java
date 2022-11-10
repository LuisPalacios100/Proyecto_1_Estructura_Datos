/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_1_estructura_datos;

import Clases.Medicamento;
import Clases.Paciente;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Luis Palacios
 */
public class Proyecto_1_Estructura_Datos {

    public static LinkedList<Medicamento> listaMedicamentos = medicamentosIniciales();
    public static LinkedList<Paciente> listaPacientes = pacientesIniciales();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        calcularCantidadesMedicamentos();
        
        boolean ejecutar = true;
        Scanner scanner = new Scanner(System.in);
        
        while (ejecutar)
        {
            System.out.println("\n******************************************\n"
                    + "Sistema de Medicamentos y Pacientes:\n"
                    + "\n"
                    + "Eliga una de las siguientes opciones:\n"
                    + "1. Control de Medicamentos\n"
                    + "2. Control de Pacientes\n"
                    + "\n"
                    + "Para salir, ingrese X.\n"
                    + "******************************************\n");
            
            String input = scanner.nextLine().toUpperCase();
            
            switch (input)
            {
                case "1":
                    controlMedicamentos();
                    break;
                case "2":
                    controlPacientes();
                    break;
                case "X":
                    System.out.println("Hasta luego!");
                    ejecutar = false;
                    break;
            }
        }
    }
    
    public static void controlMedicamentos(){
        System.out.flush();
        Scanner scanner = new Scanner(System.in);
        
        
        System.out.println("\n******************************************\n"
                + "Control de Medicamentos\n"
                + "\n"
                + "Eliga una de las siguientes opciones:\n"
                + "1. Enlistar medicamentos\n"
                + "2. Agregar un nuevo medicamento\n"
                + "3. Eliminar un medicamento\n"
                + "4. Eliminar todos los medicamentos sin asignar\n"
                + ""
                + "Para volver al menu anterior, ingrese X.\n"
                + "******************************************\n");
        
        String input = scanner.nextLine().toUpperCase();
        
        switch (input){
            case "1":
                imprimirMedicamentos();
                break;
            case "2":
                agregarMedicamento();
                break;
            case "3":
                eliminarMedicamento();
                break;
            case "4":
                eliminarMedicamentosSinAsignar();
                break;
            case "X":
                break;
            default:
                controlMedicamentos();
                break;
        }
    }
    
    public static void imprimirMedicamentos(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n******************************************\n"
                + "Medicamentos en sistema:"
                + "\n");
        for (int i = 0 ; i < listaMedicamentos.size(); i++){
            System.out.println("Nombre: " + listaMedicamentos.get(i).nombre + " | Cantidad: " + listaMedicamentos.get(i).cantidad_asignados);
        }
        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlMedicamentos();
    }
    
    public static void agregarMedicamento(){
        Medicamento nuevo = new Medicamento();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\nIngrese el nombre del medicamento:\n");
        nuevo.nombre = scanner.nextLine();
        nuevo.cantidad_asignados = 0;
        
        listaMedicamentos.add(nuevo);

        System.out.println("\nMedicamento ingresado al sistema. Ingrese cualquier valor para volver.");
        scanner.nextLine();
        controlMedicamentos();
    }
    
    public static void eliminarMedicamento(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el nombre del medicamento a borrar:\n");
        String input = scanner.nextLine().toUpperCase();
        
        for (int i = 0; i < listaMedicamentos.size(); i++){
            Medicamento medicamento = listaMedicamentos.get(i);
            String nombre = medicamento.nombre.toUpperCase();
            
            if(nombre.equals(input)){
                if(medicamento.cantidad_asignados == 0)
                {
                    listaMedicamentos.remove(i);
                    System.out.println("\nMedicamento eliminado. Ingrese cualquier valor para volver.");
                    scanner.nextLine();
                    controlMedicamentos();
                    return;
                }
                else if(medicamento.cantidad_asignados != 0)
                {
                    buscarYEliminarMedicamentosAsignados(input);
                    listaMedicamentos.remove(i);
                    System.out.println("\nMedicamento eliminado y listas de medicamentos de pacientes actualizados. Ingrese cualquier valor para volver.");
                    scanner.nextLine();
                    controlMedicamentos();
                    return;
                }
            }
        }
        System.out.println("\nMedicamento no encontrado, por favor revisar el nombre. Ingrese cualquier valor para volver.");
        controlMedicamentos();
    }
    
    public static void eliminarMedicamentosSinAsignar(){
        int contador = 0;
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < listaMedicamentos.size(); i++){
            Medicamento medicamento = listaMedicamentos.get(i);
            if(medicamento.cantidad_asignados == 0)
            {
                listaMedicamentos.remove(i);
                contador += 1;
            }
        }
        if (contador > 0){
            System.out.println("\nSe eliminaron " + contador + " medicamentos no asignados a ningun paciente. Ingrese cualquier valor para volver.");
            scanner.nextLine();
            controlMedicamentos();
        }
        else{
            System.out.println("\nNo se encontraron medicamentos sin ninguna asignacion. Ingrese cualquier valor para volver.");
            scanner.nextLine();
            controlMedicamentos();
        }
    }
    
    public static void buscarYEliminarMedicamentosAsignados(String medicamento)
    {
        for (int i = 0; i < listaPacientes.size(); i++){
            Paciente paciente = listaPacientes.get(i);
            LinkedList<String> medicamentos = paciente.listaMedicamentos;
            for (int y = 0; y < medicamentos.size() ; y++){
                String med = medicamentos.get(y).toUpperCase();
                if (med.equals(medicamento)){
                    medicamentos.remove(y);
                }
            }
            listaPacientes.get(i).listaMedicamentos = medicamentos;
        }
    }
    
    public static void controlPacientes(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n******************************************\n"
                + "Control de Pacientes\n"
                + "\n"
                + "Eliga una de las siguientes opciones:\n"
                + "1. Enlistar pacientes\n"
                + "2. Ingresar un nuevo paciente\n"
                + "3. Enlistar medicamentos de un paciente\n"
                + "4. Agregar medicamento a un paciente\n"
                + "5. Eliminar medicamento a un paciente\n"
                + "6. Eliminar paciente\n"
                + "\n"
                + "Para volver al menu anterior, ingrese X.\n"
                + "******************************************\n");
        
        String input = scanner.nextLine().toUpperCase();
        
        switch (input){
            case "1":
                imprimirPacientes();
                break;
            case "2":
                ingresarNuevoPaciente();
                calcularCantidadesMedicamentos();
                break;
            case "3":
                enlistarMedicamentosDePaciente();
                break;
            case "4":
                agregarMedicamentoAPaciente();
                calcularCantidadesMedicamentos();
                break;
            case "5":
                eliminarMedicamentoAPaciente();
                calcularCantidadesMedicamentos();
                break;
            case "6":
                eliminarPaciente();
                calcularCantidadesMedicamentos();
                break;
            case "X":
                break;
            default:
                controlPacientes();
                break;
        }
    }
    
    public static void imprimirPacientes(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n******************************************\n"
                + "Pacientes en sistema:\n"
                + "\n");
        for (int i = 0 ; i < listaPacientes.size(); i++){
            System.out.println("Nombre: " + listaPacientes.get(i).nombre + " | Medicamentos: " + listaPacientes.get(i).listaMedicamentos);
        }
        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
    }
    
    public static boolean medicamentoExiste(String nombre){
        LinkedList<String> meds = new LinkedList<>();
        for (int i = 0; i < listaMedicamentos.size(); i++){
            meds.add(listaMedicamentos.get(i).nombre.toUpperCase());
        }
        
        return meds.contains(nombre.toUpperCase());
    }
    
    public static boolean pacienteExiste(String nombre){
        LinkedList<String> pacientes = new LinkedList<>();
        for (int i = 0; i < listaPacientes.size(); i++){
            pacientes.add(listaPacientes.get(i).nombre.toUpperCase());
        }
        
        return pacientes.contains(nombre.toUpperCase());
    }
    
    public static boolean medicamentoYaAsignado (String nombre, int index){
        LinkedList<String> medicamentos = new LinkedList<>();
        for (int i = 0; i < listaPacientes.get(index).listaMedicamentos.size(); i++){
            medicamentos.add(listaPacientes.get(index).listaMedicamentos.get(i).toUpperCase());
        }
        return medicamentos.contains(nombre);
    }
    
    public static void ingresarNuevoPaciente(){
        Scanner scanner = new Scanner(System.in);
        Paciente paciente = new Paciente();
        
        System.out.println("\nIngrese el nombre del paciente:\n");
        paciente.nombre = scanner.nextLine();
        
        if (pacienteExiste(paciente.nombre) == false){
            System.out.println("\nIngrese la lista de medicamentos prescritos, separados por comas \",\":\n");
            String medicamentos = scanner.nextLine();
        
            String[] arreglo = medicamentos.split(",");
            LinkedList<String> lista = new LinkedList<>();
        
            for (String i : arreglo){
                if (medicamentoExiste(i)){
                    lista.add(i);
                }
            else{
                System.out.println("El medicamento \"" + i + "\" no existe en sistema. Debe agregarlo primero.");
            }
        }
        
        paciente.listaMedicamentos = lista;
        listaPacientes.add(paciente);
        
        System.out.println("\nPaciente ingresado al sistema.");
        }
        else{
            System.out.println("\nEl nombre ingresado ya existe en sistema y no se puede ingresar de nuevo.");
        }
        
        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
        
    }
    
    public static void enlistarMedicamentosDePaciente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el nombre del paciente:\n");
        String input = scanner.nextLine().toUpperCase();
        
        if (pacienteExiste(input)){
            for (int i = 0; i < listaPacientes.size(); i++){
                if (input.equals(listaPacientes.get(i).nombre.toUpperCase())){
                    System.out.println("\nMedicamentos: " + listaPacientes.get(i).listaMedicamentos);
                    break;
                }
            }
        }
        else{
            System.out.println("\nEl nombre ingresado no corresponde a ningun paciente en sistema.");
        }

        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
    }
    
    public static void agregarMedicamentoAPaciente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el nombre del paciente:\n");
        String nombrePaciente = scanner.nextLine().toUpperCase();
        
        if (pacienteExiste(nombrePaciente.toUpperCase())){
            System.out.println("\nIngrese el medicamento a recetar:\n");
            String nombreMedicamento = scanner.nextLine();
            
            if (medicamentoExiste(nombreMedicamento.toUpperCase())){
                for (int i = 0; i < listaPacientes.size(); i++){
                    if (nombrePaciente.equals(listaPacientes.get(i).nombre.toUpperCase())){
                        if (medicamentoYaAsignado(nombreMedicamento.toUpperCase(), i) == false){
                            listaPacientes.get(i).listaMedicamentos.add(nombreMedicamento);
                            System.out.println("\nEl medicamento ha sido agregado al paciente.");
                            break;
                        }
                        else {
                            System.out.println("\nEl medicamento ya esta asignado al paciente, no es necesario volver a agregarlo.");
                            break;
                        }
                    }
                }
            }
            else{
                System.out.println("\nEl nombre ingresado no corresponde a ningun medicamento en sistema.");
            }
        }
        else{
            System.out.println("\nEl nombre ingresado no corresponde a ningun paciente en sistema.");
        }

        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
    }
    
    public static void eliminarMedicamentoAPaciente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el nombre del paciente:\n");
        String nombrePaciente = scanner.nextLine().toUpperCase();
        
        if (pacienteExiste(nombrePaciente.toUpperCase())){
            for (int i = 0; i < listaPacientes.size(); i++){
                if (nombrePaciente.equals(listaPacientes.get(i).nombre.toUpperCase())){
                    if (listaPacientes.get(i).listaMedicamentos.size()>0){
                        System.out.println("\nEl paciente tiene los siguientes medicamentos asignados: " + listaPacientes.get(i).listaMedicamentos);
                        System.out.println("\nIngrese el medicamento a eliminar:\n");
                        String nombreMedicamento = scanner.nextLine();
                        
                        if (medicamentoYaAsignado(nombreMedicamento.toUpperCase(), i)){
                            for (int y = 0; y < listaPacientes.get(i).listaMedicamentos.size(); y++){
                                if (listaPacientes.get(i).listaMedicamentos.get(y).toUpperCase().equals(nombreMedicamento.toUpperCase())){
                                    listaPacientes.get(i).listaMedicamentos.remove(y);
                                    System.out.println("\nEl medicamento ha sido eliminado para el paciente.");
                                    break;
                                }
                            }
                        }
                        else{
                            System.out.println("\nEl medicamento ingresado no está asignado al paciente, no es necesario ningún cambio.");
                            break;
                        }
                    }
                    else{
                        System.out.println("\nEl paciente no tiene ningun medicamento asignado");
                        break;
                    }
                    break;
                }
            }
        }
        else{
            System.out.println("\nEl nombre ingresado no corresponde a ningun paciente en sistema.");
        }

        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
    }
    
    public static void eliminarPaciente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nIngrese el nombre del paciente:\n");
        String nombrePaciente = scanner.nextLine().toUpperCase();
        
        if (pacienteExiste(nombrePaciente.toUpperCase())){
            for (int i = 0; i < listaPacientes.size(); i++){
                if (nombrePaciente.equals(listaPacientes.get(i).nombre.toUpperCase())){
                    listaPacientes.remove(i);
                    System.out.println("\nPaciente eliminado del sistema.");
                    break;
                }
            }
        }
        else{
            System.out.println("\nEl nombre ingresado no corresponde a ningun medicamento en sistema.");
        }

        System.out.println("\nIngrese cualquier valor para volver.");
        scanner.nextLine();
        controlPacientes();
    }
    
    public static void calcularCantidadesMedicamentos(){
        for (int i = 0 ; i < listaMedicamentos.size(); i++){
            Medicamento check = listaMedicamentos.get(i);
            int cantidad = 0;
            
            for (int y = 0 ; y < listaPacientes.size(); y++){
                Paciente paciente = listaPacientes.get(y);
                LinkedList<String> medicamentos = paciente.listaMedicamentos;
                for (int u = 0 ; u < medicamentos.size() ; u++){
                    if (medicamentos.get(u).equals(check.nombre)){
                        cantidad += 1;
                    }
                }
            }
            
            listaMedicamentos.get(i).cantidad_asignados = cantidad; 
        }
    }
        

    // Valores iniciales para prueba
    public static LinkedList medicamentosIniciales(){
        LinkedList<Medicamento> lista = new LinkedList<>();
        String[] nombres = {"Ibuprofeno","Acetaminofem","Clorfenamina"};
        
        for (String i : nombres){
            Medicamento med = new Medicamento();
            med.nombre = i;
            med.cantidad_asignados = 0;
            lista.add(med);
        }
        
        return lista;
    }
    
    public static LinkedList pacientesIniciales(){
        LinkedList<Paciente> lista = new LinkedList<>();
        String[] nombres = {"Juan","Julia","Joel","Jessica"};
        
        for (int i=0; i <= 3 ; i++){
            Paciente nuevo = new Paciente();
            nuevo.nombre = nombres[i];
            
            LinkedList<String> medicamentos = new LinkedList<>();
            
            switch (i){
                case 0:
                    medicamentos.add("Ibuprofeno");
                    medicamentos.add("Clorfenamina");
                    break;
                case 1:
                    medicamentos.add("Ibuprofeno");
                    break;
                case 2:
                    medicamentos.add("Acetaminofem");
                    break;
                case 3:
                    medicamentos.add("Ibuprofeno");
                    break;
            }
            nuevo.listaMedicamentos = medicamentos;
            lista.add(nuevo);
        }
        return lista;
    }
}
