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

package com.postmodern.postmoderndungeon.items.potions.elixirs;

import com.postmodern.postmoderndungeon.actors.buffs.Buff;
import com.postmodern.postmoderndungeon.actors.buffs.ToxicImbue;
import com.postmodern.postmoderndungeon.actors.hero.Hero;
import com.postmodern.postmoderndungeon.effects.particles.PoisonParticle;
import com.postmodern.postmoderndungeon.items.potions.AlchemicalCatalyst;
import com.postmodern.postmoderndungeon.items.potions.PotionOfToxicGas;
import com.postmodern.postmoderndungeon.sprites.ItemSpriteSheet;

public class ElixirOfToxicEssence extends Elixir {
	
	{
		image = ItemSpriteSheet.ELIXIR_TOXIC;
	}
	
	@Override
	public void apply(Hero hero) {
		Buff.affect(hero, ToxicImbue.class).set(ToxicImbue.DURATION);
		hero.sprite.emitter().burst(PoisonParticle.SPLASH, 10);
	}
	
	@Override
	protected int splashColor() {
		return 0xFF00B34A;
	}
	
	@Override
	public int value() {
		//prices of ingredients
		return quantity * (30 + 40);
	}
	
	public static class Recipe extends com.postmodern.postmoderndungeon.items.Recipe.SimpleRecipe {
		
		{
			inputs =  new Class[]{PotionOfToxicGas.class, AlchemicalCatalyst.class};
			inQuantity = new int[]{1, 1};
			
			cost = 6;
			
			output = ElixirOfToxicEssence.class;
			outQuantity = 1;
		}
		
	}
	
}
