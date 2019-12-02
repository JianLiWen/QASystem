package preprocessing.stopWordsFilter;

import preprocessing.utility.FileOperate;
import preprocessing.utility.GetFileName;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StopWordsFilter {
	List<String> data1 = new ArrayList<String>(); // ԭ����
	List<String> data2 = new ArrayList<String>(); // ����ʹ���(ͣ�ôʹ��˺������)
	List<String> datasolved = new ArrayList<String>(); // ͣ�ôʹ��˺������
	List<String> words = new ArrayList<String>(); // ����
	List<String> props = new ArrayList<String>(); // ����
	List<String> StopwordList = new ArrayList<String>(); // ͣ�ô��б�
	List<String> StopwordPropsList = new ArrayList<String>(); // ͣ�ôʴ����б�
	boolean IsStopword = false; // �ж��Ƿ���ͣ�ô�
	boolean IsNull = false; // �ж��Ƿ��Ǿ���ͣ�ôʹ��˺��������Ϊ��
	
	/**
	 * ׼������������ͣ�ôʴʵ䣬��������Ҫ���˵��Ĵ��ԡ�������Ҫ������ı��ļ�
	 * @param inputFilePath
	 */
	private void prepare(String inputFilePath){
		System.out.println("׼�����������С���");
		/**
		 * ����ͣ�ôʱ��˴�ʹ�õ��ǹ�����ͣ�ôʱ���չ
		 */
		String dictionaryPath = ".\\file\\Dictionary\\ͣ�ôʴʵ�\\������ͣ�ôʱ���չ.txt";	// ���ͣ�ôʴʵ��·��
		File file1 = new File(dictionaryPath);
		FileOperate.ReadFromTxt(StopwordList, file1, false);

		/**
		 * ����ͣ�ôʴ��Ա����������еĴ��ԵĴʣ��������˵�
		 */
		// ������
		StopwordPropsList.add("w");
		StopwordPropsList.add("wkz");
		StopwordPropsList.add("wky");
		StopwordPropsList.add("wyz");
		StopwordPropsList.add("wyy");
		StopwordPropsList.add("wj");
		StopwordPropsList.add("ww");
		StopwordPropsList.add("wt");
		StopwordPropsList.add("wd");
		StopwordPropsList.add("wf");
		StopwordPropsList.add("wn");
		StopwordPropsList.add("wm");
		StopwordPropsList.add("ws");
		StopwordPropsList.add("wp");
		StopwordPropsList.add("wb");
		StopwordPropsList.add("wh");

		// ���
		StopwordPropsList.add("p");
		StopwordPropsList.add("pba"); // ��
		StopwordPropsList.add("pbei"); // ��

		// ����
		StopwordPropsList.add("c");
		StopwordPropsList.add("cc"); // ��������

		// ����
		StopwordPropsList.add("u");
		StopwordPropsList.add("uzhe");
		StopwordPropsList.add("ule");
		StopwordPropsList.add("uguo");
		StopwordPropsList.add("ude1");
		StopwordPropsList.add("ude2");
		StopwordPropsList.add("ude3");
		StopwordPropsList.add("usuo");
		StopwordPropsList.add("udeng");
		StopwordPropsList.add("uyy");
		StopwordPropsList.add("udh");
		StopwordPropsList.add("uls");
		StopwordPropsList.add("uzhi");
		StopwordPropsList.add("ulian");

		// ����
		StopwordPropsList.add("m");
		StopwordPropsList.add("mq");

		// ����
		StopwordPropsList.add("q");
		StopwordPropsList.add("qv");
		StopwordPropsList.add("qt");

		// ̾��
		StopwordPropsList.add("e");

		// ������
		StopwordPropsList.add("y");

		// ������
		StopwordPropsList.add("o");

		// �ַ���
		StopwordPropsList.add("x");
		StopwordPropsList.add("xe");
		StopwordPropsList.add("xs");
		StopwordPropsList.add("xm");
		StopwordPropsList.add("xu");

		// ����
		StopwordPropsList.add("r");
		StopwordPropsList.add("rr");
		StopwordPropsList.add("rz");
		StopwordPropsList.add("rzt");
		StopwordPropsList.add("rzs");
		StopwordPropsList.add("rzv");
		StopwordPropsList.add("ry");
		StopwordPropsList.add("ryt");
		StopwordPropsList.add("rys");
		StopwordPropsList.add("ryv");
		StopwordPropsList.add("rg");

		/**
		 * ���ִʺ�����۶���data1��
		 */
		File file2 = new File(inputFilePath);
		FileOperate.ReadFromTxt(data1, file2, true);
		System.out.println("׼����ɡ���");
	}
	
	/**
	 * ִ��ͣ�ôʹ��˲��������ѹ��˺�Ľ�������TXT�ļ���
	 * @param outputFilePath ����ļ���·��
	 */
	private void filter(String outputFilePath){
		/**
		 * ��ѭ������ͣ�ôʵĹ���
		 */
		System.out.println("ͣ�ôʹ����С�������");
		for (int i = 0; i < data1.size(); i++) {
			
			if (data1.get(i) != "") {
				String StrSolved = "";	// ���ڱ�����������еķ�ͣ�ô�
				String[] aWordsandProps = data1.get(i).split(" "); // ���ո�Ծ��ӽ��л���
				IsNull = true; // Ϊ���жϹ��˵���ͣ�ô�֮���Ƿ��ǿմ�
				// foreachѭ������ȡ��һ�������е����дʣ�������ͣ�ôʹ��˴���
				
				for (String wordandprop : aWordsandProps) {
					IsStopword = false;		// ���ڱ�ǵ�ǰ���Ƿ�Ϊͣ�ô�
					if (wordandprop != "")  // �жϴ���-���Զ��Ƿ�Ϊ�մ�
					{
						if (wordandprop.contains("/")) // ���˵����Ŀո���������
						{
							String[] Str = wordandprop.split("/"); // �Ѵ���ʹ��Էֿ�
							if (Str.length == 2) // ���˵����С�/���Ĵ�
							{
								String word = Str[0]; // ����
								String prop = Str[1]; // ����

								// ���˵��ض����Ե�ͣ�ô�
								for (int j = 0; j < StopwordPropsList.size(); j++) {
									if (prop.equals(StopwordPropsList.get(j))) {
										IsStopword = true;
										break;
									}
								}

								// ���˵�ͣ�ôʱ������ͣ�ô�(���û��ͨ���ض����Թ��˵��Ļ�����ô��ͨ��ͣ�ôʱ���й���)
								if (!IsStopword) {
									for (int j = 0; j < StopwordList.size(); j++) {
										if (word.equals(StopwordList.get(j))) {
											IsStopword = true;
											break;
										}
									}
								}

								if (!IsStopword) {
									data2.add(wordandprop);
									words.add(word);
									props.add(prop);
									StrSolved += word + " ";

									IsNull = false;
								}

							}
						}
					}
				} // foreach

				if (!IsNull) {
					// �˴��ļ���Ŀմ���Ϊ�˽�ÿһ�������ÿմ�����
					data2.add("");
					words.add("");
					props.add("");
				}
				datasolved.add(StrSolved);

			} // if
		} // for
		System.out.println("ͣ�ôʹ�����ɣ����ڽ����д�뵽txt�ļ��С���");

		/**
		 * ���ͣ�ôʹ��˺�Ľ��
		 */
		File file3 = new File(outputFilePath);
		// ���������ļ��ĸ�·�������ڣ��򴴽�֮
		File fileParent = file3.getParentFile();
		if(!fileParent.exists()) {
			fileParent.mkdirs();
		}
		FileOperate.Print2Txt(datasolved, file3, false);
		System.out.println("д�����");
	}
	
	public void run(String inputFilePath, String outputFilePath){
		prepare(inputFilePath);
		filter(outputFilePath);
	}
	
	public String singleRun(String line){
		/**
		 * ����ͣ�ôʱ��˴�ʹ�õ��ǹ�����ͣ�ôʱ���չ
		 */
		String dictionaryPath = ".\\file\\Dictionary\\ͣ�ôʴʵ�\\������ͣ�ôʱ���չ.txt";	// ���ͣ�ôʴʵ��·��
		File file1 = new File(dictionaryPath);
		FileOperate.ReadFromTxt(StopwordList, file1, false);

		/**
		 * ����ͣ�ôʴ��Ա����������еĴ��ԵĴʣ��������˵�
		 */
		// ������
		StopwordPropsList.add("w");
		StopwordPropsList.add("wkz");
		StopwordPropsList.add("wky");
		StopwordPropsList.add("wyz");
		StopwordPropsList.add("wyy");
		StopwordPropsList.add("wj");
		StopwordPropsList.add("ww");
		StopwordPropsList.add("wt");
		StopwordPropsList.add("wd");
		StopwordPropsList.add("wf");
		StopwordPropsList.add("wn");
		StopwordPropsList.add("wm");
		StopwordPropsList.add("ws");
		StopwordPropsList.add("wp");
		StopwordPropsList.add("wb");
		StopwordPropsList.add("wh");

		// ���
		StopwordPropsList.add("p");
		StopwordPropsList.add("pba"); // ��
		StopwordPropsList.add("pbei"); // ��

		// ����
		StopwordPropsList.add("c");
		StopwordPropsList.add("cc"); // ��������

		// ����
		StopwordPropsList.add("u");
		StopwordPropsList.add("uzhe");
		StopwordPropsList.add("ule");
		StopwordPropsList.add("uguo");
		StopwordPropsList.add("ude1");
		StopwordPropsList.add("ude2");
		StopwordPropsList.add("ude3");
		StopwordPropsList.add("usuo");
		StopwordPropsList.add("udeng");
		StopwordPropsList.add("uyy");
		StopwordPropsList.add("udh");
		StopwordPropsList.add("uls");
		StopwordPropsList.add("uzhi");
		StopwordPropsList.add("ulian");

		// ����
		StopwordPropsList.add("m");
		StopwordPropsList.add("mq");

		// ����
		StopwordPropsList.add("q");
		StopwordPropsList.add("qv");
		StopwordPropsList.add("qt");

		// ̾��
		StopwordPropsList.add("e");

		// ������
		StopwordPropsList.add("y");

		// ������
		StopwordPropsList.add("o");

		// �ַ���
		StopwordPropsList.add("x");
		StopwordPropsList.add("xe");
		StopwordPropsList.add("xs");
		StopwordPropsList.add("xm");
		StopwordPropsList.add("xu");

		// ����
		StopwordPropsList.add("r");
		StopwordPropsList.add("rr");
		StopwordPropsList.add("rz");
		StopwordPropsList.add("rzt");
		StopwordPropsList.add("rzs");
		StopwordPropsList.add("rzv");
		StopwordPropsList.add("ry");
		StopwordPropsList.add("ryt");
		StopwordPropsList.add("rys");
		StopwordPropsList.add("ryv");
		StopwordPropsList.add("rg");
		
		
		String StrSolved = "";	// ���ڱ�����������еķ�ͣ�ô�
		String[] aWordsandProps = line.split(" "); // ���ո�Ծ��ӽ��л���
		IsNull = true; // Ϊ���жϹ��˵���ͣ�ô�֮���Ƿ��ǿմ�
		// foreachѭ������ȡ��һ�������е����дʣ�������ͣ�ôʹ��˴���
		
		for (String wordandprop : aWordsandProps) {
			IsStopword = false;		// ���ڱ�ǵ�ǰ���Ƿ�Ϊͣ�ô�
			if (wordandprop != "")  // �жϴ���-���Զ��Ƿ�Ϊ�մ�
			{
				if (wordandprop.contains("/")) // ���˵����Ŀո���������
				{
					String[] Str = wordandprop.split("/"); // �Ѵ���ʹ��Էֿ�
					if (Str.length == 2) // ���˵����С�/���Ĵ�
					{
						String word = Str[0]; // ����
						String prop = Str[1]; // ����

						// ���˵��ض����Ե�ͣ�ô�
						for (int j = 0; j < StopwordPropsList.size(); j++) {
							if (prop.equals(StopwordPropsList.get(j))) {
								IsStopword = true;
								break;
							}
						}

						// ���˵�ͣ�ôʱ������ͣ�ô�(���û��ͨ���ض����Թ��˵��Ļ�����ô��ͨ��ͣ�ôʱ���й���)
						if (!IsStopword) {
							for (int j = 0; j < StopwordList.size(); j++) {
								if (word.equals(StopwordList.get(j))) {
									IsStopword = true;
									break;
								}
							}
						}

						if (!IsStopword) {
							data2.add(wordandprop);
							words.add(word);
							props.add(prop);
							StrSolved += word + " ";

							IsNull = false;
						}

					}
				}
			}
		} // foreach
		
		return StrSolved;
	}

	public static void main(String[] args) {
		String rootPath = ".\\file\\Corpus\\"; // ��������ļ��ĸ�·��
		String inputFilePath = rootPath + "FAQ\\segmentation\\A_delEmpNRep_Segmentation.txt";	 // �����ļ���·��������Ҫ������ļ�·��
		String outputFilePath = rootPath + "FAQ"; // ����ͣ�ôʹ��˺�������ı�
		
		/*
		 * ��ȡ�����ļ����ļ���
		 */
		GetFileName gfp = new GetFileName();
		String fileName = gfp.getFileName(inputFilePath);
		
		outputFilePath += ("\\stopWordsFilter\\" + fileName + "_stopWordsFilter.txt");
		
		StopWordsFilter swf = new StopWordsFilter();
		swf.run(inputFilePath, outputFilePath);
	}

}

