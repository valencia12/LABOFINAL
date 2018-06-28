/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame {

    public JLabel lblCodigo, lblNombre, lblPrecio, lblCantidad, lblTipo, lblDisponiblidad;

    public JTextField codigo, nombre, precio, cantidad;
    public JComboBox tipo;

    ButtonGroup disponibilidad = new ButtonGroup();
    public JRadioButton no;
    public JRadioButton si;
    public JTable resultados;

    public JPanel table;

    public JButton buscar, eliminar, insertar, limpiar, actualizar;

    private static final int ANCHOC = 130, ALTOC = 30;

    DefaultTableModel tm;

    public Consulta() {
        super("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblCodigo);
        container.add(lblNombre);
        container.add(lblPrecio);
        container.add(lblCantidad);
        container.add(lblTipo);
        container.add(lblDisponiblidad);
        container.add(codigo);
        container.add(nombre);
        container.add(precio);
        container.add(cantidad);
        container.add(tipo);
        container.add(si);
        container.add(no);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(limpiar);
        container.add(table);
        setSize(600, 600);
        eventos();

    }

    private void agregarLabels() {
        lblCodigo = new JLabel("Codigo");
        lblNombre = new JLabel("Nombre");
        lblPrecio = new JLabel("Precio");
        lblCantidad = new JLabel("Cantidad");
        lblTipo = new JLabel("Tipo");
        lblDisponiblidad = new JLabel("Disponiblidad");
        lblCodigo.setBounds(10, 10, ANCHOC, ALTOC);
        lblNombre.setBounds(10, 50, ANCHOC, ALTOC);
        lblPrecio.setBounds(10, 100, ANCHOC, ALTOC);
        lblCantidad.setBounds(10, 150, ANCHOC, ALTOC);
        lblTipo.setBounds(10, 200, ANCHOC, ALTOC);
        lblDisponiblidad.setBounds(10, 250, ANCHOC, ALTOC);
    }

    private void formulario() {
        codigo = new JTextField();
        nombre = new JTextField();
        precio = new JTextField();
        cantidad = new JTextField();
        tipo = new JComboBox();
        si = new JRadioButton("si", true);
        no = new JRadioButton("no");
        resultados = new JTable();
        buscar = new JButton("Buscar");
        insertar = new JButton("Insertar");
        eliminar = new JButton("Eliminar");
        actualizar = new JButton("Actualizar");
        limpiar = new JButton("Limpiar");

        table = new JPanel();

        tipo.addItem("Fruta");
        tipo.addItem("Verdura");
        tipo.addItem("Bebida");
        tipo.addItem("Dulce");

        disponibilidad = new ButtonGroup();
        disponibilidad.add(si);
        disponibilidad.add(no);
        //-------------------------------------------
        codigo.setBounds(140, 10, ANCHOC, ALTOC);
        nombre.setBounds(140, 50, ANCHOC, ALTOC);
        precio.setBounds(140, 100, ANCHOC, ALTOC);
        cantidad.setBounds(140, 150, ANCHOC, ALTOC);
        tipo.setBounds(140, 200, ANCHOC, ALTOC);
        si.setBounds(140, 250, 50, ALTOC);
        no.setBounds(210, 250, 50, ALTOC);

        buscar.setBounds(300, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 300, ANCHOC, ALTOC);
        actualizar.setBounds(150, 300, ANCHOC, ALTOC);
        eliminar.setBounds(300, 300, ANCHOC, ALTOC);
        limpiar.setBounds(450, 300, ANCHOC, ALTOC);

        resultados = new JTable();
        table.setBounds(10, 350, 500, 200);
        table.add(new JScrollPane(resultados));

    }

    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return String.class;
                }
            }
        };

        tm.addColumn("Codigo");
        tm.addColumn("Nombre");
        tm.addColumn("Tipo");
        tm.addColumn("Disponibilidad");
        tm.addColumn("Precio");
        tm.addColumn("Cantidad");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getCodigo(), fi.getNombre(), fi.getTipo(), fi.getDisponibilidad(), fi.getPrecio(), fi.getCantidad()});
        }

        resultados.setModel(tm);

    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), Double.parseDouble(precio.getText()), Integer.parseInt(cantidad.getText()), tipo.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.create(f)) {
                    JOptionPane.showMessageDialog(null, "Producto registrado correctamente.");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema con la creación de este procuto.");
                }
            }
        });

        actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), Double.parseDouble(precio.getText()), Integer.parseInt(cantidad.getText()), tipo.getSelectedItem().toString(), true);

                if (no.isSelected()) {
                    f.setDisponibilidad(false);
                }

                if (fd.update(f)) {
                    JOptionPane.showMessageDialog(null, "producto modificado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar de este producto.");
                }
            }
        });

        eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(), nombre.getText(), Double.parseDouble(precio.getText()),Integer.parseInt(cantidad.getText()),tipo.getSelectedItem().toString(), true);
                if (fd.delete(codigo.getText())) {
                    JOptionPane.showMessageDialog(null, "producto eliminado con éxito");
                    limpiarCampos();
                    llenarTabla();
                } else {
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar este producto.");
                }
            }
        });

        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if (f == null) {
                    JOptionPane.showMessageDialog(null, "El producto buscado no ha sido encontrado");
                } else {

                    codigo.setText(f.getCodigo());
                    nombre.setText(f.getNombre());
                    precio.setText(Double.toString(f.getPrecio()));
                    cantidad.setText(Integer.toString(f.getCantidad()));
                    tipo.setSelectedItem(f.getTipo());
                    
                    if (f.getDisponibilidad()) {
                        si.setSelected(true);
                    } else {
                        no.setSelected(true);
                    }
                }
            }
        });

        limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
    }

    public void limpiarCampos() {
        codigo.setText("");
        nombre.setText("");
        precio.setText("");
        cantidad.setText("");
        tipo.setSelectedItem("Fruta");
    }

}
