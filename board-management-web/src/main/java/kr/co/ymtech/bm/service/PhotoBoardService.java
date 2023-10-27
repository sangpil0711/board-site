package kr.co.ymtech.bm.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ymtech.bm.controller.dto.BoardGetDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardDTO;
import kr.co.ymtech.bm.controller.dto.PhotoBoardGetDTO;
import kr.co.ymtech.bm.repository.IPhotoBoardRepository;
import kr.co.ymtech.bm.repository.vo.BoardVO;
import kr.co.ymtech.bm.repository.vo.PhotoBoardVO;

@Service
public class PhotoBoardService implements IPhotoBoardService {

	private final IPhotoBoardRepository photoBoardRepository;

	@Autowired
	public PhotoBoardService(IPhotoBoardRepository IphotoBoardRepository) {
		this.photoBoardRepository = IphotoBoardRepository;
	}

	@Override
	public List<PhotoBoardGetDTO> findPhotoBoard(Integer category) {
		List<PhotoBoardVO> vo = photoBoardRepository.findPhotoBoard(category);
		List<PhotoBoardGetDTO> dto = new ArrayList<>();

		for (PhotoBoardVO photoVO : vo) {
			PhotoBoardGetDTO PhotoDTO = new PhotoBoardGetDTO();
			PhotoDTO.setIndex(photoVO.getIndex());
			PhotoDTO.setTitle(photoVO.getTitle());
			PhotoDTO.setText(photoVO.getText());
			PhotoDTO.setUserId(photoVO.getUserId());
			PhotoDTO.setCategory(photoVO.getCategory());
			PhotoDTO.setCreateDate(new Date(photoVO.getCreateDate()));
			dto.add(PhotoDTO);
		}

		return dto;
	}

	@Override
	public Integer savePhotoBoard(PhotoBoardDTO photo) {
		PhotoBoardVO vo = new PhotoBoardVO();
		vo.setTitle(photo.getTitle());
		vo.setText(photo.getText());
		vo.setCategory(photo.getCategory());

		if (photo.getCreateDate() == null) {
			vo.setCreateDate(new Date().getTime());
		} else {
			vo.setCreateDate(photo.getCreateDate());
		}

		Integer save = photoBoardRepository.savePhotoBoard(vo);

		return save;
	}

	@Override
	public Integer updatePhotoBoard(PhotoBoardGetDTO photo) {

		PhotoBoardVO vo = new PhotoBoardVO(); // dto -> vo 변환
		vo.setIndex(photo.getIndex());
		vo.setTitle(photo.getTitle());
		vo.setText(photo.getText());

		Integer update = photoBoardRepository.updatePhotoBoard(vo);

		return update;
	}

	@Override
	public Integer deletePhotoBoard(Integer index) {

//		commentRepository.deleteAllComment(index);

		return photoBoardRepository.deletePhotoBoard(index);
	}

	@Override
	public PhotoBoardGetDTO searchByPhotoIndex(Integer index) {
		PhotoBoardVO vo = photoBoardRepository.searchByPhotoIndex(index);

		PhotoBoardGetDTO dto = new PhotoBoardGetDTO(); // vo -> dto 변환
		dto.setIndex(vo.getIndex());
		dto.setTitle(vo.getTitle());
		dto.setText(vo.getText());
		dto.setUserId(vo.getUserId());
		dto.setCategory(vo.getCategory());
		dto.setCreateDate(new Date(vo.getCreateDate()));
		return dto;
	}

}
