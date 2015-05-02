import java.io.File;
import java.util.ArrayList;

public interface ParseType {
	public ParserType getType();
	public ArrayList<Object> getValueFromXML(String searchItem);
}