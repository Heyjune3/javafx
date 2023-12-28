package practice;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController implements Initializable{

	@FXML private TableView<TableCL> tableView;
	@FXML private TableColumn<TableCL, String> name;
	@FXML private TableColumn<TableCL, Integer> ko;
	@FXML private TableColumn<TableCL, Integer> math;
	@FXML private TableColumn<TableCL, Integer> eng;
	
	@Override
	public void initialize(URL location, ResourceBundle bundle) {
		ObservableList<TableCL> tableList = FXCollections.observableArrayList(
	    new TableCL("홍길동A",40, 60, 80),
	    new TableCL("홍길동B",60, 80, 40),
	    new TableCL("홍길동C",80, 40, 60)
				);
	    
		
		ObservableList<TableColumn<TableCL, ?>> columnList = tableView.getColumns();
		System.out.println(columnList);
//		Field[] fields = TableCL.class.getDeclaredFields();
//		for(int i = 0; i< fields.length; i++) {
//		String name = fields[i].getName();
//		TableColumn<TableCL, ?> columnName = columnList.get(i);
//		columnName.setCellValueFactory(new PropertyValueFactory<>(name));
//		}
		TableColumn<TableCL, ?> columnName = columnList.get(0);
		columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		TableColumn<TableCL, ?> columnKo = columnList.get(1);
		columnKo.setCellValueFactory(new PropertyValueFactory<>("ko"));
		TableColumn<TableCL, ?> columnMath = columnList.get(2);
		columnMath.setCellValueFactory(new PropertyValueFactory<>("math"));
		TableColumn<TableCL, ?> columnEng = columnList.get(3);
		columnEng.setCellValueFactory(new PropertyValueFactory<>("Eng"));
		tableView.setItems(tableList);
	
	}

}

