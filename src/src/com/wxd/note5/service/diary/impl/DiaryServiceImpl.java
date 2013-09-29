package com.wxd.note5.service.diary.impl;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxd.note5.dao.diary.DiaryDAO;
import com.wxd.note5.model.diary.Diary;
import com.wxd.note5.service.diary.DiaryService;
import com.wxd.note5.service.diary.DiaryTitleParser;
import com.wxd.note5.util.EncryptDESTool;
import com.wxd.note5.util.PaginatedResult;

@Service
@Transactional
public class DiaryServiceImpl  implements DiaryService{ 

	private static final String ENCODING_NAME = "UTF-8";
	private String[] dayOfWeek = {"","日","一","二","三","四","五","六"};
	
	@Resource(name="diaryDAOImpl")
	DiaryDAO diaryDAO;
	
	@Autowired
	EncryptDESTool tool;
	
	@Override
	public PaginatedResult<Diary> getList(int pageNo, int pageSize) throws Exception {
		
		PaginatedResult<Diary> result = this.diaryDAO.getList(pageNo, pageSize);
		List<Diary> list = result.getResult();
		for(Diary diary : list){
			//进行解密操作
			byte[] plainTextBytes =  tool.decrypte(diary.getContent());
			if(plainTextBytes == null)  continue;
			diary.setPlainText(new String(plainTextBytes,ENCODING_NAME));
		}
		
		return result;
	}

	@Override
	public void newDiary() throws Exception{
		Diary diary = new Diary();
		//设置标题
		diary.setTitle(getDefaultTitle());
		
		//创建日期
		diary.setLastModify(new Date());
		diary.setCreateDate(new Date());
		
		
		//更新加密内容
		String plainText = diary.getPlainText();		
		if(plainText != null && !"".equals(plainText.trim())){
			byte[] cipherBytes = tool.encrypte(diary.getPlainText().getBytes(ENCODING_NAME));
			diary.setContent(cipherBytes);
		}
		
		//更新diaryOn字段
		updateDiaryOnByTitle(diary);
		
		this.diaryDAO.saveDiary(diary);
	}

	/**
	 * 得到默认的日志标题
	 * @return  当前日期的格式
	 */
	private String getDefaultTitle() {
		Calendar c = Calendar.getInstance();
		StringBuffer buf = new StringBuffer();
		buf.append(c.get(Calendar.YEAR));
		buf.append("年");
		buf.append(c.get(Calendar.MONTH));
		buf.append("月");
		buf.append(c.get(Calendar.DATE));
		buf.append("日 ");
		buf.append("星期");
		buf.append(dayOfWeek[c.get(Calendar.DAY_OF_WEEK)]);
		
		return buf.toString();
	}

	@Override
	public Diary getDiaryById(int id) throws Exception{
	
		Diary diary =  this.diaryDAO.getDiaryById(id);
		if(diary == null) return null;
		
		byte[] plainTextBytes =  tool.decrypte(diary.getContent());
		if(plainTextBytes != null){
			diary.setPlainText(new String(plainTextBytes,ENCODING_NAME));
		} 
		
		return diary;
	}


	/**
	 * 更新日志内容
	 */
	@Override
	public void updateDiary(Diary diary) throws Exception{
		String plainText = diary.getPlainText();
		//设置密文
		if(plainText != null && !"".equals(plainText.trim())){
			byte[] cipherBytes = tool.encrypte(diary.getPlainText().getBytes(ENCODING_NAME));
			diary.setContent(cipherBytes);
		}
		
		//根据diary title来更新diaryOn字段
		updateDiaryOnByTitle(diary);
		
		 this.diaryDAO.updateDiary( diary); 
	}	
	
	private void  updateDiaryOnByTitle(Diary diary) throws ParseException{
		//根据diary title来更新diaryOn字段
		Date diaryOn = DiaryTitleParser.getTitleDate(diary.getTitle());
		if(diaryOn != null){
			diary.setDiaryOn(diaryOn);
		}else{
			diary.setDiaryOn(new Date());
		}
	}

	@Override
	public void deleteDiary(int id) {
		this.diaryDAO.deleteDiary(id);
	} 
}