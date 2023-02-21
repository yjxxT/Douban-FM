package fm.douban.service;

import fm.douban.model.Singer;

import java.util.List;

public interface SingerService {
    //增加一个歌手
    public Singer addSinger(Singer singer);
    //根据歌手id查询歌手
    public  Singer get(String singerId);
    //查询所有歌手
    public List<Singer> getAll();
    //修改歌手
    public boolean modify(Singer singer);
    //根据id主键删除歌手
    public boolean delete(String singerId);
}
