package com.leegh.alibaba.music;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 
 * user_id(用户ID),song_id(歌曲ID),gmt_create(时间),action_type(行为类型：1，播放；2，下载，3，收藏),Ds(记录收集日)
 */
public class TransTime {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream(new File("data/initdata/mars_tianchi_user_actions.csv"))));
		BufferedWriter bw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(new File("data/transtime/action.csv"))));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {
			String[] cols = line.split(",");
			String date = sdf.format(new Date(Integer.parseInt(cols[2]) * 1000L));
			sb.append(cols[0] + "," + cols[1] + "," + date + cols[3] + "," + cols[4] + "\n");
			bw.write(sb.toString());
			sb.delete(0, sb.length());
		}
		if (br != null)
			br.close();
		if (bw != null)
			bw.close();
	}
}
