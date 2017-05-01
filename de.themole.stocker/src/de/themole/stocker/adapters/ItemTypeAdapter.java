package de.themole.stocker.adapters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import de.themole.stocker.dao.Item;
import de.themole.stocker.dao.Update;

public class ItemTypeAdapter extends TypeAdapter<Item> {

	@Override
	public Item read(final JsonReader in) throws IOException {
		final Item item = new Item();

		in.beginObject();
		while (in.hasNext()) {
			String name = in.nextName().toLowerCase();
			switch (name) {
			case "name":
				item.setName(in.nextString());
				break;
			case "description":
				item.setDescription(in.nextString());
				break;
			case "altids":
				item.setAltIds(Arrays.asList(in.nextString().split(",")));
				break;
			case "created":
				// TODO
				break;
			case "id":
				item.setId(in.nextLong());
				break;
			case "images":
				// TODO
				break;
			case "documentation":
			default:
				// TODO
				break;
			}
		}

		if (item.getCreated() == null) {
			item.setCreated(Calendar.getInstance());
		}

		in.endObject();

		return item;
	}

	@Override
	public void write(final JsonWriter out, final Item item) throws IOException {
		out.beginObject();
		out.name("id").value(item.getId());
		out.name("name").value(item.getName());
		out.name("description").value(item.getDescription());
		out.name("altIDs").value(String.join(",", item.getAltIds()));
		out.name("created").value(item.getCreated().getTimeInMillis());
		out.name("updates");
		out.beginArray();
		List<Update> lu = item.getUpdates();
		for (Update su : lu) {
			out.beginObject();
			out.name("timestamp").value(su.getTimestamp().getTimeInMillis());
			out.name("log").value(su.getLogtext());
			out.endObject();
		}
		out.endArray();
		out.endObject();
	}
}
