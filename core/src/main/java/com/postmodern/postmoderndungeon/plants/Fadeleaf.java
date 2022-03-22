/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * Shattered Pixel Dungeon
 * Copyright (C) 2014-2021 Evan Debenham
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package com.postmodern.postmoderndungeon.plants;

import com.postmodern.postmoderndungeon.Dungeon;
import com.postmodern.postmoderndungeon.actors.Char;
import com.postmodern.postmoderndungeon.actors.buffs.Buff;
import com.postmodern.postmoderndungeon.actors.hero.Hero;
import com.postmodern.postmoderndungeon.actors.hero.HeroSubClass;
import com.postmodern.postmoderndungeon.actors.mobs.Mob;
import com.postmodern.postmoderndungeon.effects.CellEmitter;
import com.postmodern.postmoderndungeon.effects.Speck;
import com.postmodern.postmoderndungeon.items.artifacts.TimekeepersHourglass;
import com.postmodern.postmoderndungeon.items.scrolls.ScrollOfTeleportation;
import com.postmodern.postmoderndungeon.messages.Messages;
import com.postmodern.postmoderndungeon.scenes.InterlevelScene;
import com.postmodern.postmoderndungeon.sprites.ItemSpriteSheet;
import com.postmodern.postmoderndungeon.utils.GLog;
import com.watabou.noosa.Game;

public class Fadeleaf extends Plant {
	
	{
		image = 10;
		seedClass = Seed.class;
	}
	
	@Override
	public void activate( final Char ch ) {
		
		if (ch instanceof Hero) {
			
			((Hero)ch).curAction = null;
			
			if (((Hero) ch).subClass == HeroSubClass.WARDEN){
				
				if (Dungeon.bossLevel()) {
					GLog.w( Messages.get(ScrollOfTeleportation.class, "no_tele") );
					return;
					
				}

				TimekeepersHourglass.timeFreeze timeFreeze = Dungeon.hero.buff(TimekeepersHourglass.timeFreeze.class);
				if (timeFreeze != null) timeFreeze.disarmPressedTraps();
				Swiftthistle.TimeBubble timeBubble = Dungeon.hero.buff(Swiftthistle.TimeBubble.class);
				if (timeBubble != null) timeBubble.disarmPressedTraps();
				
				InterlevelScene.mode = InterlevelScene.Mode.RETURN;
				InterlevelScene.returnDepth = Math.max(1, (Dungeon.depth - 1));
				InterlevelScene.returnPos = -2;
				Game.switchScene( InterlevelScene.class );
				
			} else {
				ScrollOfTeleportation.teleportChar((Hero) ch);
			}
			
		} else if (ch instanceof Mob && !ch.properties().contains(Char.Property.IMMOVABLE)) {

			ScrollOfTeleportation.teleportChar(ch);

		}
		
		if (Dungeon.level.heroFOV[pos]) {
			CellEmitter.get( pos ).start( Speck.factory( Speck.LIGHT ), 0.2f, 3 );
		}
	}
	
	public static class Seed extends Plant.Seed {
		{
			image = ItemSpriteSheet.SEED_FADELEAF;

			plantClass = Fadeleaf.class;
		}
	}
}
