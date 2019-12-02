package preprocessing.chineseWordSegmentation.code;

import com.sun.jna.Library;
import com.sun.jna.Native;
import preprocessing.utility.GetFileName;

import java.io.*;

//import utils.SystemParas;

public class NlpirTest {

	// �ñ������ڱ���Դ�ļ������ں��������и����ɵ����ļ�ȡ���ʵ����֣�����Դ�ļ�����
	String sourceFileName = null;

	public String getSourceFileName() {
		return sourceFileName;
	}

	public void setSourceFileName(String sourceFileName) {
		this.sourceFileName = sourceFileName;
	}

	// ����ӿ�CLibrary���̳���com.sun.jna.Library
	public interface CLibrary extends Library {
		// ���岢��ʼ���ӿڵľ�̬����

		CLibrary Instance = (CLibrary) Native.loadLibrary(
				new File("file\\win64\\NLPIR").getAbsolutePath(), CLibrary.class);// �趨dll��·��������ʹ����һ����ʱ�ļ�������������֪���·��������»�ȡ����·��

		public int NLPIR_Init(String sDataPath, int encoding, String sLicenceCode);

		public String NLPIR_ParagraphProcess(String sSrc, int bPOSTagged);

		public String NLPIR_GetKeyWords(String sLine, int nMaxKeyLimit, boolean bWeightOut);

		public String NLPIR_GetFileKeyWords(String sLine, int nMaxKeyLimit, boolean bWeightOut);
		//����û��ʻ�
		public int NLPIR_AddUserWord(String sWord);
		//ɾ���û��ʻ�
		public int NLPIR_DelUsrWord(String sWord);

		public String NLPIR_GetLastErrorMsg();

		public void NLPIR_Exit();
		// �����û��Զ���ʵ䣺�Զ���ʵ�·����bOverwrite=true��ʾ�����ǰ���Զ���ʵ䣬false��ʾ��ӵ���ǰ�Զ���ʵ��
		public int NLPIR_ImportUserDict(String path, boolean bOverwrite);
	}

	public static String transString(String aidString, String ori_encoding, String new_encoding) {
		try {
			return new String(aidString.getBytes(ori_encoding), new_encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void run(String inputFilePath, String outputFilePath) {
		String argu = ".\\file\\ICTCLAS2019";	// ����Data�ļ����ڵ�·����ע�����ʵ������޸ġ�
		String system_charset = "GBK";// GBK----0
//		String system_charset = "UTF-8";
		int charset_type = 1;

		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("��ʼ��ʧ�ܣ�fail reason is " + nativeBytes);
			return;
		}

		try {
			File comment = new File(inputFilePath);// ��ȡԭʼ����
			File wordSegmentComment = new File(outputFilePath);// ����ִʺ���ı�
			// ���������ļ��ĸ�·�������ڣ��򴴽�֮
			File fileParent = wordSegmentComment.getParentFile();
			if(!fileParent.exists()) {
				fileParent.mkdirs();
			}

			if (comment.isFile() && comment.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(comment), system_charset);// ���ǵ������ʽ
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;

				System.out.println("�������ϲ����зִ�����Ա�ע��������");
				OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(wordSegmentComment));// ����ļ��Ѵ��ڣ�����ԭ������
				BufferedWriter bufferedWriter = new BufferedWriter(writer);
				// ѭ������ԭʼ���۵�ÿһ�У����зִʴ������ѷִʵĽ�����б��档
				int nCount = CLibrary.Instance.NLPIR_ImportUserDict(".\\file\\Dictionary\\�Զ���ִʴʵ�\\�ִʱ���չ.txt",true);
				System.out.println(String.format("�ѵ���%d���û��ʻ�", nCount));
				while ((lineTxt = bufferedReader.readLine()) != null) {
					// ����NLPIRϵͳ����Ӧ�ĺ������зִ�����Ա�ע����
					nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(lineTxt, 0);
					// ���ִʵĽ��д�뵽txt�ĵ���
					bufferedWriter.write(nativeBytes + "\r\n");
				}
				bufferedWriter.close();
				System.out.println("�ִ�����Ա�ע������ɣ�������浽txt�ļ��С�");

				bufferedReader.close();
			}

			CLibrary.Instance.NLPIR_Exit();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public static String singleSentence(String line) {
		String argu = ".\\file\\ICTCLAS2019";	// ����Data�ļ����ڵ�·����ע�����ʵ������޸ġ�
		String system_charset = "GBK";// GBK----0
//		String system_charset = "UTF-8";
		int charset_type = 1;

		int init_flag = CLibrary.Instance.NLPIR_Init(argu, charset_type, "0");
		String nativeBytes = null;

		if (0 == init_flag) {
			nativeBytes = CLibrary.Instance.NLPIR_GetLastErrorMsg();
			System.err.println("��ʼ��ʧ�ܣ�fail reason is " + nativeBytes);
			return null;
		}

		// ����NLPIRϵͳ����Ӧ�ĺ������зִ�����Ա�ע����
		nativeBytes = CLibrary.Instance.NLPIR_ParagraphProcess(line, 1);

		CLibrary.Instance.NLPIR_Exit();

		return nativeBytes;
	}

	public static void main(String[] args) throws Exception {
		String rootPath = ".\\file\\Corpus\\"; // ��������ļ��ĸ�·��
		String inputFilePath = rootPath + "FAQ\\deleteEmptyNRepetition\\A_delEmpNRep.txt";// ��ȡԭʼ����
		String outputFilePath = rootPath + "FAQ";// ����ִʺ�������ı�

		/*
		 * ��ȡ�����ļ����ļ���
		 */
		GetFileName gfp = new GetFileName();
		String fileName = gfp.getFileName(inputFilePath);

		outputFilePath += ("\\segmentation\\" + fileName + "_Segmentation.txt");

		// ���зִ�
		NlpirTest nlpir = new NlpirTest();
		nlpir.run(inputFilePath, outputFilePath);
	}
}
