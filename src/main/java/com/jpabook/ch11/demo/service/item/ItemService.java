package com.jpabook.ch11.demo.service.item;

import com.jpabook.ch11.demo.domain.item.Item;
import com.jpabook.ch11.demo.domain.item.ItemRepository;
import com.jpabook.ch11.demo.service.item.dto.request.BookSaveRequest;
import com.jpabook.ch11.demo.service.item.dto.response.ItemFindResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	public void saveItem(BookSaveRequest item) {
		itemRepository.save(item.toEntity());
	}

	public List<ItemFindResponse> findItems() {
		List<ItemFindResponse> itemFindResponses = new ArrayList<>();
		itemRepository.findAll()
				.forEach(item -> itemFindResponses.add(ItemFindResponse.of(item)));
		return itemFindResponses;
	}

	public ItemFindResponse findItem(Long itemId) {
		Item item = itemRepository.findById(itemId)
				.orElseThrow(() -> new IllegalArgumentException(String.format("해당 상품(%s)가 없습니다", itemId)));
		return ItemFindResponse.of(item);
	}

}
