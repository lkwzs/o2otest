package com.o2o.util;

import com.o2o.dto.ImageHolder;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * 图片文件生成
 * */

public class ImageUtil {


	/*缩略图生成*/
	public static String generateThumbnail(ImageHolder thumbnail, String targetAddr) {
		String realFileName = FileUtil.getRandomFileName();/*生成文件名*/
		String extension = getFileExtension(thumbnail);/*得到文件后缀名*/
		makeDirPath(targetAddr);/*如果文件夹不存在则生成*/
		String relativeAddr = targetAddr + realFileName + extension; /*生成文件址*/

		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		try {
			/*根据thumbnail的流生成文件*/
			Thumbnails.of(thumbnail.getImage())
//					.watermark(Positions.BOTTOM_LEFT, ImageIO.read()) 水印生成
					.size(200, 200).outputQuality(0.25f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		/*返回地址值*/
		return relativeAddr;
	}


	/*缩略图生成*/
	public static String generateNormalImg(ImageHolder thumbnail, String targetAddr) {
		String realFileName = FileUtil.getRandomFileName();
		String extension = getFileExtension(thumbnail);
		makeDirPath(targetAddr);
		String relativeAddr = targetAddr + realFileName + extension;
		File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
		try {
			Thumbnails.of(thumbnail.getImage()).size(337, 640).outputQuality(0.5f).toFile(dest);
		} catch (IOException e) {
			throw new RuntimeException("创建缩略图失败：" + e.toString());
		}
		return relativeAddr;
	}

	/*图片生成*/
	public static List<String> generateNormalImgs(List<ImageHolder> imgs, String targetAddr) {
		int count = 0;
		List<String> relativeAddrList = new ArrayList<String>();
		if (imgs != null && imgs.size() > 0) {
			makeDirPath(targetAddr);
			for (ImageHolder img : imgs) {
				String realFileName = FileUtil.getRandomFileName();
				String extension = getFileExtension(img);
				String relativeAddr = targetAddr + realFileName + count + extension;
				File dest = new File(FileUtil.getImgBasePath() + relativeAddr);
				count++;
				try {
					Thumbnails.of(img.getImage()).size(600, 300).outputQuality(0.5f).toFile(dest);
				} catch (IOException e) {
					throw new RuntimeException("创建图片失败：" + e.toString());
				}
				relativeAddrList.add(relativeAddr);
			}
		}
		return relativeAddrList;
	}

	/*生成文件目录*/
	private static void makeDirPath(String targetAddr) {
		String realFileParentPath = FileUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/*提取后缀*/
	private static String getFileExtension(ImageHolder cFile) {
		String originalFileName = cFile.getImageName();
		return originalFileName.substring(originalFileName.lastIndexOf("."));
	}
}
