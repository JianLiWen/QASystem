package preprocessing.dictionaryManage;

import preprocessing.utility.FileOperate;
import preprocessing.utility.GetFileName;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ����ȥ��ȥ�ز���
 * 
 * @author Alex
 *
 */
public class DeleteEmptyNRepetition {

	List<String> afterDeleteEmpty = new ArrayList<String>(); // ���ڱ���ȥ��֮�������
	List<String> afterDeleteRepetition = new ArrayList<String>(); // ���ڱ���ȥ��֮�������
	
	List<String> answer = new ArrayList<String>();	//
	List<Integer> lineNum = new ArrayList<Integer>();
	/**
	 * �������ݲ�����ȥ�մ���
	 * 
	 * @param inputFilePath
	 *            �����ļ�·��
	 * @param afterDeleteEmpty
	 *            ���ڱ���ȥ��֮�������
	 */
	private void deleteEmpty(File inFile) {
		System.out.println("�����ļ�������ȥ�մ����С���");
		FileOperate.ReadFromTxt(afterDeleteEmpty, inFile, true); // �ö��뺯���Ѿ�������ȥ�յĹ���
		System.out.println("ȥ�մ�����ϡ�");
	}

	/**
	 * ȥ�ش���
	 */
	private void deleteRepetition() {
		boolean IsRepeated = false; // ������ȥ�ش����ʱ���ж��Ƿ�������ظ�������

		int count = 1;
		
		System.out.println("ִ��ȥ�ش����С�������");
		afterDeleteRepetition.add(afterDeleteEmpty.get(0));
		lineNum.add(0);
		System.out.println(afterDeleteEmpty.size());
		for (int i = 1; i < afterDeleteEmpty.size(); i++) {
			IsRepeated = false;
			for (int j = 0; j < i; j++) {
				if (afterDeleteEmpty.get(i).equals(afterDeleteEmpty.get(j))) {
					IsRepeated = true;
					break;
				}
			}
			if (!IsRepeated) {
				afterDeleteRepetition.add(afterDeleteEmpty.get(i));
				lineNum.add(count);
			}
			++count;
		}
//		System.out.println(afterDeleteRepetition);
		System.out.println("ȥ�ش�����ϡ�");
	}

	public void run(String inputFilePath, String outputFilePath) {
		
		File inFile = new File(inputFilePath);
		
		// �����
		File inFileAnswer = new File("file\\Corpus\\FAQ\\rawCorpus\\A.txt");
		FileOperate.ReadFromTxt(answer, inFileAnswer, true);
		System.out.println(answer.size());
		
		// ȥ�ղ���
		deleteEmpty(inFile);
		// ȥ�ز���
		deleteRepetition();
		System.out.println(afterDeleteRepetition.size());
		
		// ���
		System.out.println("��ȥ��ȥ�صĽ��д�뵽txt�С���");
		File outFile = new File(outputFilePath);
		// ���������ļ��ĸ�·�������ڣ��򴴽�֮
		File fileParent = outFile.getParentFile();
		if(!fileParent.exists()) {
			fileParent.mkdirs();
		}
		FileOperate.Print2Txt(afterDeleteRepetition, outFile, false);
		
		// ��������Ӧ�Ĵ�
		File outFileAnswer = new File("file\\Corpus\\FAQ\\deleteEmptyNRepetition\\A_delEmpNRep.txt");
		try {
			OutputStreamWriter writer;
			writer = new OutputStreamWriter(new FileOutputStream(outFileAnswer, false)); // ���appendΪtrue�������׷��;���Ϊfalse���򸲸�ԭ�����ݡ�
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			// ��ͣ�ôʹ��˺�Ľ��д�뵽txt�ĵ���
			System.out.println("lineNum: " + lineNum.size());
			for (int i = 0; i < lineNum.size(); ++i) {
				bufferedWriter.write(answer.get(lineNum.get(i)) + "\r\n");
			}
			bufferedWriter.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("д�����");
	}

	public static void main(String[] args) {
		String rootPath = ".\\file\\Corpus\\"; // ��������ļ��ĸ�·��
		String inputFilePath = rootPath + "FAQ\\rawCorpus\\Q.txt"; // �����ļ�·��
		String outputFilePath = rootPath + "FAQ"; // ����ȥ��ȥ�غ�������ı�
		
		/*
		 * ��ȡ�����ļ����ļ���
		 */
		GetFileName gfp = new GetFileName();
		String fileName = gfp.getFileName(inputFilePath);
		
		outputFilePath += ("\\deleteEmptyNRepetition\\" + fileName + "_delEmpNRep.txt");
		
		DeleteEmptyNRepetition denr = new DeleteEmptyNRepetition(); // ����һ��ʵ��ȥ��ȥ�ز����Ķ���
		denr.run(inputFilePath, outputFilePath);
	}

}
