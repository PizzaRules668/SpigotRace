package dev.csutter.race.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import dev.csutter.race.models.Course;
import dev.csutter.race.utils.CourseUtils;

public class AddCheckpointCommand implements CommandExecutor {
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command");
            return true;
        }

        if (args.length != 1) return false;

        // Get the course they want to add the checkpoint to
        Course course = CourseUtils.getCourse(args[0]);

        if (!CourseUtils.checkIfOwner(course, (Player) sender)) {
            sender.sendMessage("You must be the player who created the track");
            return false;
        }

        // Add the checkpoint based on player pos
        course.addCheckpoint(((Entity)sender).getLocation());

        // Update the Courses
        CourseUtils.updateCourse(course.getID(), course);
        
        return true;
    }
}
