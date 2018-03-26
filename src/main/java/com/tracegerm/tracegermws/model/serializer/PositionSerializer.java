package com.tracegerm.tracegermws.model.serializer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.tracegerm.tracegermws.model.dto.PositionDTO;
import com.vividsolutions.jts.geom.Point;

import java.io.IOException;

public class PositionSerializer extends JsonSerializer<Point> {
    public PositionSerializer() {
    }

    @Override
    public void serialize(Point value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonGenerationException {
        PositionDTO positionDTO = new PositionDTO(value.getX(), value.getY());
        jgen.writeObject(positionDTO);
    }
}
