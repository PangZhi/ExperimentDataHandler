import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// Copyright (c) 2014, Jing Fan. All Rights Reserved

/**
 * @author Jing Fan
 * @brief This class is used to data structured for experiment.
 * @TODO By default, we have two dimension besides times.
 */
public class Result2csv {
	// The files should be organized as dataDir/times/dimension1_2_3.txt;
	// Path to the data directory.
	String dataDir = null;
	// The map from attribute to values. The key is the combination of attributes, separated by ",". For example, the 
	// attribute names are programname, dataname, the key is "pagerank, google.txt". The value is the combination of 
	// values, separated by ",". For example, the value names are "totaltime, setuptime", the value is "150, 25".
	private HashMap<String, String> dataMap = null;
	// Whether it is the first file.
	boolean firstFile = true;
	
	/**
	 * @brief Constructor.
	 * @param path The path to the data directory.
	 */
	public Result2csv(String path) {
		// Initialize dataMap.
		dataMap = new HashMap<String, String>();
		dataDir = path;
	}
	
	/**
	 * @brief Generate data map.
	 */
	public void generate() {
		File dir = new File(dataDir);
		File[] files1 = dir.listFiles();
		for (File file1 : files1) {
			File[] files2 = file1.listFiles();
			for (File file2 : files2) {
				String[] ss = file2.getName().split("_");
				List<String> sList = Arrays.asList(ss);
				List<String> ssList = new ArrayList<String>(sList);
				ssList.add(file1.getName());
				String key = StringUtils.join(ssList, ", ");
				List<String> valList = FileHandler.getValues(file2);
				String val = StringUtils.join(valList, ", ");
				dataMap.put(key, val);
			}
		}
	}
	
	/**
	 * @brief Output csv.
	 */
	public void output() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File(dataDir+ "/" + "data.csv")));
			Iterator<Entry<String, String>> iter = dataMap.entrySet().iterator();
			while (iter.hasNext()) {
				Map.Entry entry = iter.next();
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				bw.write(key + ", " + val);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
