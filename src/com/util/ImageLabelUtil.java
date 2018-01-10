package com.util;

import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.model.Constant;

public class ImageLabelUtil {

    /**
     * 批量获取图片label
     *
     * @param path      图片路径
     * @param prizeType 奖项
     * @param prizeName 奖品
     * @return
     */
    public static List<JLabel> getImageLabelList(String path, String prizeType, String prizeName) {
        String imageNamePath = ConversionUtil.getImageNamePath(prizeType, prizeName);
        List<JLabel> imageLabelList = new ArrayList<JLabel>();
        File file = new File(path);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return null;
        }
        // 遍历，目录下的所有文件
        for (File f : files) {
            if (f.getName().endsWith(imageNamePath)) {
                imageLabelList.add(new JLabel(setImageIconSize(new ImageIcon(f.getAbsolutePath()))));
            }
        }
        return imageLabelList;
    }

    public static ImageIcon setImageIconSize(ImageIcon icon) {
        int showImageWidth = Integer
                .parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SHOW_IMAGE_WIDTH));
        int showImageHeight = Integer
                .parseInt(PropertiesUtil.getValueByKey(Constant.CONFIG_PROPERTIES_FILE_PATH, Constant.SHOW_IMAGE_HEIGHT));
        icon.setImage(icon.getImage().getScaledInstance(showImageWidth, showImageHeight, Image.SCALE_DEFAULT));
        return icon;
    }
    
}
