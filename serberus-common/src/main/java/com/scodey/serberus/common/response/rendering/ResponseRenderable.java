package com.scodey.serberus.common.response.rendering;

import java.io.IOException;

public interface ResponseRenderable {
  void render(ResponseRenderer renderer) throws IOException;
}
