/*
 * Copyright 2014 OpenMarket Ltd
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

import org.matrix.androidsdk.rest.model.Event;
import java.util.HashMap;

/**
 * Class representing private data that the user has defined for a room.
 */
public class RoomAccountData implements java.io.Serializable{

    // The tags the user defined for this room.
    // The key is the tag name. The value, the associated MXRoomTag object.
    private HashMap<String, RoomTag> tags = new HashMap<String, RoomTag>();

    /**
     * Process an event that modifies room account data (like m.tag event).
     * @param event an event
     */
    public void handleEvent(Event event) {
        if (event.type.equals(Event.EVENT_TYPE_TAGS)) {
            tags = RoomTag.roomTagsWithTagEvent(event);
        }
    }
}
