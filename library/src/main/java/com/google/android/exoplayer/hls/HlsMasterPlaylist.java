/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer.hls;

import android.net.Uri;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents an HLS master playlist.
 */
public final class HlsMasterPlaylist extends HlsPlaylist {

  public final List<Variant> variants;
  public final List<AlternateMedia> alternateMedias;

  public HlsMasterPlaylist(Uri baseUri, List<Variant> variants, List<AlternateMedia> alternateMedias) {
    super(baseUri, HlsPlaylist.TYPE_MASTER);
    this.variants = variants;
    this.alternateMedias = alternateMedias;

    // Setup variant alternate medias
    for (Variant v: variants) {
      if (v.audio != null) {
        for (AlternateMedia a: alternateMedias) {
          if (a.groupID != null && v.audio.equals(a.groupID)) {
            v.alternateMedias.add(a);
          }
        }
      }
    }
  }
}
