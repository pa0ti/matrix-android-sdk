/*
 * Copyright 2015 OpenMarket Ltd
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.matrix.androidsdk.data;

import android.util.Log;
import org.matrix.androidsdk.rest.model.Event;
import org.matrix.androidsdk.rest.model.RoomTags;
import org.matrix.androidsdk.util.JsonUtils;
import java.util.HashMap;

/**
 * Class representing a room tag.
 */
public class RoomTag implements java.io.Serializable {

    private static final String LOG_TAG = "RoomTag";

    /**
     The name of a tag.
     */
    public String mName;

    /**
     Try to parse order as NSNumber.
     Provides nil if the items cannot be parsed.
     */
    public Double mOrder;

    /**
     * RoomTag creator.
     * @param aName the tag name.
     * @param anOrder the tag order
     */
    public RoomTag(String aName , Double anOrder) {
        mName = aName;
        mOrder = anOrder;
    }

    /**
     * Extract a list of tags from a room tag event.
     * @param event a room tag event (which can contains several tags)
     * @return a dictionary containing the tags the user defined for one room.
     */
    public static HashMap<String, RoomTag>roomTagsWithTagEvent(Event event)
    {
        HashMap<String, RoomTag> tags = new HashMap<String, RoomTag>();

        try {
            RoomTags roomtags = JsonUtils.toRoomTags(event.content);

            if ((null != roomtags.tags) && (0 != roomtags.tags.size())) {
                for (String tagName : roomtags.tags.keySet()) {
                    HashMap<String, Double> params = roomtags.tags.get(tagName);

                    if (params.containsKey("order")) {
                        tags.put(tagName, new RoomTag(tagName, params.get("order")));
                    }
                }
            }
        }
        catch (Exception e) {
            Log.d(LOG_TAG, "roomTagsWithTagEvent fails " + e.getLocalizedMessage() );
        }

        return tags;
    }
}
